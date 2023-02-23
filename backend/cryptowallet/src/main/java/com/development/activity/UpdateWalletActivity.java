package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import com.development.dynamodb.models.CryptoCurrenciesModel;
import com.development.dynamodb.models.Users;
import com.development.dynamodb.models.Wallet;
import com.development.exceptions.*;
import com.development.models.requests.AddWalletRequest;
import com.development.models.requests.UpdateWalletRequest;
import com.development.models.results.AddWalletResult;
import com.development.models.results.UpdateWalletResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.ArrayList;

public class UpdateWalletActivity implements RequestHandler<UpdateWalletRequest, UpdateWalletResult>  {
    private final UsersDao usersDao;
    private final WalletDao walletDao;

    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public UpdateWalletActivity(UsersDao usersDao, WalletDao walletDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.walletDao = walletDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public UpdateWalletResult handleRequest(
            final UpdateWalletRequest updateWalletRequest, Context context) {

        if (updateWalletRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (updateWalletRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(updateWalletRequest.getToken());

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
        if (!(user.getAdmin() || user.getEmail().equals(updateWalletRequest.getUserId()) )) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // try to find crypto
        Wallet findedWallet = walletDao.getWallet(updateWalletRequest.getUserId(), updateWalletRequest.getWalletName());
        if (findedWallet == null) {
            throw new WalletNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Wallet doesn't exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        Wallet newWallet = new Wallet();
        newWallet.setUserId(updateWalletRequest.getUserId());
        newWallet.setWalletName(updateWalletRequest.getWalletName());
        newWallet.setWalletDescription(updateWalletRequest.getWalletDescription());
        newWallet.setCryptosCount(updateWalletRequest.getCryptosCost());
        newWallet.setCryptosCost(updateWalletRequest.getCryptosCost());
        newWallet.setCryptocurrenciesList(updateWalletRequest.getCryptocurrenciesList());

        Wallet updatedWallet = walletDao.updateWallet(newWallet);
        if (updatedWallet == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        return UpdateWalletResult.builder()
                .withUserId(updatedWallet.getUserId())
                .withWalletName(updatedWallet.getWalletName())
                .withWalletDescription(updatedWallet.getWalletDescription())
                .withCryptosCount(updatedWallet.getCryptosCount())
                .withCryptosCost(updatedWallet.getCryptosCost())
                .withCryptocurrenciesList(updatedWallet.getCryptocurrenciesList())
                .build();
    }
}
