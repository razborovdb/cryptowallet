package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.CryptoCurrencies;
import com.development.dynamodb.models.Users;
import com.development.exceptions.*;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.UpdateCryptoInAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.UpdateCryptoInAvailableCryptoResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;

public class UpdateCryptoInAvailableCryptoActivity implements RequestHandler<UpdateCryptoInAvailableCryptoRequest, UpdateCryptoInAvailableCryptoResult>  {
    private final UsersDao usersDao;
    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public UpdateCryptoInAvailableCryptoActivity(UsersDao usersDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public UpdateCryptoInAvailableCryptoResult handleRequest(
            final UpdateCryptoInAvailableCryptoRequest updateCryptoInAvailableCryptoRequest, Context context) {

        if (updateCryptoInAvailableCryptoRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (updateCryptoInAvailableCryptoRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(updateCryptoInAvailableCryptoRequest.getToken());

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
        if (!(user.getAdmin() )) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // try to find crypto
        CryptoCurrencies findedCrypto = cryptoDao.getCrypto(updateCryptoInAvailableCryptoRequest.getCryptoName());
        if (findedCrypto == null) {
            throw new CryptoNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Crypto doesn't exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        CryptoCurrencies newCrypto = new CryptoCurrencies();
        newCrypto.setCryptoName(findedCrypto.getCryptoName());
        newCrypto.setCryptoDescription(updateCryptoInAvailableCryptoRequest.getCryptoDescription());
        newCrypto.setCryptoAmount(updateCryptoInAvailableCryptoRequest.getCryptoAmount());
        newCrypto.setCryptoCost(updateCryptoInAvailableCryptoRequest.getCryptoCost());

        CryptoCurrencies updatedCrypto = cryptoDao.updateCrypto(newCrypto);
        if (updatedCrypto == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        return UpdateCryptoInAvailableCryptoResult.builder()
                .withCryptoName(updatedCrypto.getCryptoName())
                .withImage("")
                .withImageUrl("")
                .withCryptoDescription(updatedCrypto.getCryptoDescription())
                .withCryptoAmount(updatedCrypto.getCryptoAmount())
                .withCryptoCost(updatedCrypto.getCryptoCost())
                .build();
    }
}
