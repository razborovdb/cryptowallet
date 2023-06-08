package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.HistoryDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import com.development.dynamodb.models.*;
import com.development.exceptions.*;
import com.development.models.requests.AddCryptoToWalletRequest;
import com.development.models.requests.UpdateWalletRequest;
import com.development.models.results.AddCryptoToWalletResult;
import com.development.models.results.UpdateWalletResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCryptoToWalletActivity implements RequestHandler<AddCryptoToWalletRequest, AddCryptoToWalletResult>  {
    private final UsersDao usersDao;
    private final WalletDao walletDao;

    private final CryptoDao cryptoDao;
    private final HistoryDao historyDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public AddCryptoToWalletActivity(UsersDao usersDao, WalletDao walletDao, CryptoDao cryptoDao, HistoryDao historyDao) {
        this.usersDao = usersDao;
        this.walletDao = walletDao;
        this.cryptoDao = cryptoDao;
        this.historyDao = historyDao;
    }


    @Override
    public AddCryptoToWalletResult handleRequest(
            final AddCryptoToWalletRequest addCryptoToWalletRequest, Context context) {

        if (addCryptoToWalletRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (addCryptoToWalletRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(addCryptoToWalletRequest.getToken());

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
        if (!(user.getAdmin() || user.getEmail().equals(addCryptoToWalletRequest.getUserId()) )) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // try to find wallet

        Wallet findedWallet = walletDao.getWallet(addCryptoToWalletRequest.getUserId(), addCryptoToWalletRequest.getWalletName());

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


        if (cryptocurrencyList == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        if (cryptocurrencyList.isEmpty()) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        boolean isInAvailable = false;
        CryptoCurrencies availableCrypto = new CryptoCurrencies();
        for (int i = 0; i < cryptocurrencyList.size(); i++) {
            availableCrypto = cryptocurrencyList.get(i);
            if (availableCrypto.getCryptoName().equals(addCryptoToWalletRequest.getCryptoType())) {
                isInAvailable = true;
                break;
            }
        }

        if (!isInAvailable) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        List<CryptoCurrenciesModel> cryptocurrencyInWallet = findedWallet.getCryptocurrenciesList();
        if (cryptocurrencyInWallet == null) {
            cryptocurrencyInWallet = new ArrayList<>();
        }
        boolean isInWallet = false;
        for (int i = 0; i < cryptocurrencyInWallet.size(); i++) {
            if (cryptocurrencyInWallet.get(i).getCryptoName().equals(addCryptoToWalletRequest.getCryptoName())) {
                isInWallet = true;
                break;
            }
        }

        if (isInWallet) {
            throw new CryptoAlreadyInWalletException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Crypto already in wallet ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        addCryptoToWalletRequest.setCryptoCost(addCryptoToWalletRequest.getCryptoAmount() * availableCrypto.getCryptoCost());
        CryptoCurrenciesModel cryptoModel = new CryptoCurrenciesModel(addCryptoToWalletRequest.getCryptoName(), addCryptoToWalletRequest.getCryptoType(),
                addCryptoToWalletRequest.getImage(), addCryptoToWalletRequest.getImageUrl(),addCryptoToWalletRequest.getCryptoDescription(), addCryptoToWalletRequest.getCryptoAmount(),
                addCryptoToWalletRequest.getCryptoCost());
        cryptocurrencyInWallet.add(cryptoModel);
        findedWallet.setCryptosCount((double) cryptocurrencyInWallet.size());
        findedWallet.setCryptosCost(findedWallet.getCryptosCost() + addCryptoToWalletRequest.getCryptoCost());
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

        CryptoHistory cryptoHistory = new CryptoHistory();
        cryptoHistory.setUserId(user.getEmail());
        cryptoHistory.setRecordDate("ddddd");
        cryptoHistory.setWalletsList(wallets);
        historyDao.addHistoryRecord(cryptoHistory);

        walletAfterAdd = walletDao.getWallet(walletAfterAdd.getUserId(), walletAfterAdd.getWalletName());

        if (walletAfterAdd == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        return AddCryptoToWalletResult.builder()
                .withUserId(walletAfterAdd.getUserId())
                .withWalletName(walletAfterAdd.getWalletName())
                .withWalletDescription(walletAfterAdd.getWalletDescription())
                .withCryptosCount(walletAfterAdd.getCryptosCount())
                .withCryptosCost(walletAfterAdd.getCryptosCost())
                .withCryptocurrenciesList(walletAfterAdd.getCryptocurrenciesList())
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
