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
import com.development.models.requests.DeleteUserWalletRequest;
import com.development.models.requests.GetUserOneWalletRequest;
import com.development.models.results.DeleteUserWalletResult;
import com.development.models.results.GetUserOneWalletResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteUserWalletActivity implements RequestHandler<DeleteUserWalletRequest, DeleteUserWalletResult> {
    private final UsersDao usersDao;
    private final WalletDao walletDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public DeleteUserWalletActivity(UsersDao usersDao, WalletDao walletDao) {
        this.usersDao = usersDao;
        this.walletDao = walletDao;
    }


    @Override
    public DeleteUserWalletResult handleRequest(
            final DeleteUserWalletRequest deleteUserWalletRequest, Context context) {

        if (deleteUserWalletRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (deleteUserWalletRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(deleteUserWalletRequest.getToken());

        if (user == null) {
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
        if (!(user.getAdmin() || user.getEmail().equals(deleteUserWalletRequest.getEmail()))) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        Wallet wallet = walletDao.getWallet(deleteUserWalletRequest.getEmail(), deleteUserWalletRequest.getWalletName());
        if (wallet == null) {
            throw new WalletNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Wallet not found ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        wallet = walletDao.deleteWallet(wallet);
        if (wallet == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        return DeleteUserWalletResult.builder()
                .withWalletName(deleteUserWalletRequest.getWalletName())
                .build();

    }


}
