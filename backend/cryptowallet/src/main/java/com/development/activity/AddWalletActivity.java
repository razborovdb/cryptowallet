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
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.AddWalletRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.AddWalletResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.ArrayList;

public class AddWalletActivity implements RequestHandler<AddWalletRequest, AddWalletResult>  {
    private final UsersDao usersDao;
    private final WalletDao walletDao;

    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public AddWalletActivity(UsersDao usersDao, WalletDao walletDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.walletDao = walletDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public AddWalletResult handleRequest(
            final AddWalletRequest addWalletRequest, Context context) {

        if (addWalletRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (addWalletRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(addWalletRequest.getToken());

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
        if (!(user.getAdmin() || user.getEmail().equals(addWalletRequest.getUserId()) )) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // try to find crypto
        Wallet findedWallet = walletDao.getWallet(addWalletRequest.getUserId(), addWalletRequest.getWalletName());
        if (findedWallet != null) {
            throw new WalletAlreadyExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Wallet already exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        Wallet newWallet = new Wallet();
        newWallet.setUserId(addWalletRequest.getUserId());
        newWallet.setWalletName(addWalletRequest.getWalletName());
        newWallet.setWalletDescription(addWalletRequest.getWalletDescription());
        newWallet.setCryptosCount(addWalletRequest.getCryptosCost());
        newWallet.setCryptosCost(addWalletRequest.getCryptosCost());
        newWallet.setCryptocurrenciesList(new ArrayList<CryptoCurrenciesModel>());

        Wallet addedWallet = walletDao.addWallet(newWallet);
        if (addedWallet == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        return AddWalletResult.builder()
                .withUserId(addedWallet.getUserId())
                .withWalletName(addedWallet.getWalletName())
                .withWalletDescription(addedWallet.getWalletDescription())
                .withCryptosCount(addedWallet.getCryptosCount())
                .withCryptosCost(addedWallet.getCryptosCost())
                .withCryptocurrenciesList(addedWallet.getCryptocurrenciesList())
                .build();
    }
}
