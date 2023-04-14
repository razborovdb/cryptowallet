package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import com.development.dynamodb.models.CryptoCurrencies;
import com.development.dynamodb.models.CryptoCurrenciesModel;
import com.development.dynamodb.models.Users;
import com.development.dynamodb.models.Wallet;
import com.development.exceptions.*;
import com.development.models.requests.DeleteCryptoInWalletRequest;
import com.development.models.requests.UpdateCryptoInWalletRequest;
import com.development.models.results.DeleteCryptoInWalletResult;
import com.development.models.results.UpdateCryptoInWalletResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteCryptoInWalletActivity implements RequestHandler<DeleteCryptoInWalletRequest, DeleteCryptoInWalletResult>  {
    private final UsersDao usersDao;
    private final WalletDao walletDao;

    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public DeleteCryptoInWalletActivity(UsersDao usersDao, WalletDao walletDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.walletDao = walletDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public DeleteCryptoInWalletResult handleRequest(
            final DeleteCryptoInWalletRequest deleteCryptoInWalletRequest, Context context) {


        if (deleteCryptoInWalletRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (deleteCryptoInWalletRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(deleteCryptoInWalletRequest.getToken());

        if(user == null) {
            throw new BadTokenException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad token ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        user = usersDao.getUser(user.getEmail());

        if (user == null) {
            throw new BadTokenException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad token ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        if (!(user.getAdmin() || user.getEmail().equals(deleteCryptoInWalletRequest.getUserId()) )) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // try to find wallet

        Wallet findedWallet = walletDao.getWallet(deleteCryptoInWalletRequest.getUserId(), deleteCryptoInWalletRequest.getWalletName());

        if (findedWallet == null) {
            throw new WalletNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Wallet doesn't exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        // ---------------------------------------------------------------------------------------------
        List<CryptoCurrencies> cryptocurrencyList = cryptoDao.getAllCryptocurrency();



        List<CryptoCurrenciesModel> cryptocurrencyInWallet = findedWallet.getCryptocurrenciesList();
        if (cryptocurrencyInWallet == null) {
            cryptocurrencyInWallet = new ArrayList<>();
        }

        CryptoCurrenciesModel cryptoInWallet = new CryptoCurrenciesModel();
        int index = -1;
        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            if (cryptocurrencyInWallet.get(i).getCryptoName().equals(deleteCryptoInWalletRequest.getCryptoName())) {
                cryptoInWallet = cryptocurrencyInWallet.get(i);
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new CryptoNotExistInWalletException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Crypto doesn't exist in wallet ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        findedWallet.setCryptosCost(findedWallet.getCryptosCost() - cryptoInWallet.getCryptoCost());
        String cryptoName = cryptoInWallet.getCryptoName();
        cryptocurrencyInWallet.remove(index);
        findedWallet.setCryptosCount((double) cryptocurrencyInWallet.size());
        findedWallet.setCryptocurrenciesList(cryptocurrencyInWallet);

        Wallet walletAfterAdd = walletDao.updateWallet(findedWallet);
        if (walletAfterAdd == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        List<Wallet> wallets = walletDao.getAllWalletsForCustomerId(findedWallet.getUserId());
        updateAllWallets(wallets, cryptocurrencyList);


        walletAfterAdd = walletDao.getWallet(walletAfterAdd.getUserId(), walletAfterAdd.getWalletName());

        if (walletAfterAdd == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        return DeleteCryptoInWalletResult.builder()
                .withCryptoName(cryptoName)
                .build();
    }

    public void updateAllWallets(List<Wallet> wallets, List<CryptoCurrencies> cryptocurrencyList) {
        Map<String, CryptoCurrencies> map = new HashMap<>();
        if (cryptocurrencyList != null) {
            if (!cryptocurrencyList.isEmpty()) {
                for (CryptoCurrencies crypto : cryptocurrencyList) {
                    map.put(crypto.getCryptoName(), crypto);
                }
                for (int j = 0; j < wallets.size(); j++) {
                    Wallet wallet = wallets.get(j);

                    List<CryptoCurrenciesModel> cryptos = wallet.getCryptocurrenciesList();
                    if (cryptos != null) {
                        if (!cryptos.isEmpty()) {
                            for (int i = 0; i < cryptos.size(); i++) {
                                CryptoCurrenciesModel crypto = cryptos.get(i);
                                if (map.containsKey(crypto.getCryptoType())) {
                                    CryptoCurrencies cryptoCurrencies = map.get(crypto.getCryptoType());
                                    crypto.setCryptoCost(crypto.getCryptoAmount() * cryptoCurrencies.getCryptoCost());
                                    crypto.setImageUrl(cryptoCurrencies.getImageUrl());
                                    cryptos.set(i, crypto);
                                }

                            }
                        } else {
                            wallet.setCryptosCount(0.0);
                            wallet.setCryptosCost(0.0);
                        }
                    } else {
                        wallet.setCryptosCount(0.0);
                        wallet.setCryptosCost(0.0);

                    }
                    wallet.setCryptocurrenciesList(cryptos);
                    wallets.set(j, wallet);
                    walletDao.updateWallet(wallet);

                }
            }
        }
    }
}
