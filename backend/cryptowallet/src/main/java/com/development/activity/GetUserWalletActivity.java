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
import com.development.models.requests.AddWalletRequest;
import com.development.models.requests.GetUserWalletRequest;
import com.development.models.results.AddWalletResult;
import com.development.models.results.GetUserWalletResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserWalletActivity implements RequestHandler<GetUserWalletRequest, GetUserWalletResult>  {
    private final UsersDao usersDao;
    private final WalletDao walletDao;

    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public GetUserWalletActivity(UsersDao usersDao, WalletDao walletDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.walletDao = walletDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public GetUserWalletResult handleRequest(
            final GetUserWalletRequest getUserWalletRequest, Context context) {

        if (getUserWalletRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (getUserWalletRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(getUserWalletRequest.getToken());

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
        if (!(user.getAdmin() || user.getEmail().equals(getUserWalletRequest.getEmail()) )) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }



        List<Wallet> walletList = walletDao.getAllWalletsForCustomerId(getUserWalletRequest.getEmail());
        if (walletList == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        List<CryptoCurrencies> cryptocurrencyList = cryptoDao.getAllCryptocurrency();

        if (cryptocurrencyList == null) {
            return GetUserWalletResult.builder()
                    .withWalletList(walletList)
                    .build();
        }
        updateAllWallets(walletList, cryptocurrencyList);






        return GetUserWalletResult.builder()
                .withWalletList(walletList)
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
