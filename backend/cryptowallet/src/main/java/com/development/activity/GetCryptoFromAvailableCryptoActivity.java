package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.CryptoCurrencies;
import com.development.dynamodb.models.Users;
import com.development.exceptions.*;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.GetCryptoFromAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.GetCryptoFromAvailableCryptoResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;

public class GetCryptoFromAvailableCryptoActivity implements RequestHandler<GetCryptoFromAvailableCryptoRequest, GetCryptoFromAvailableCryptoResult>  {
    private final UsersDao usersDao;
    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public GetCryptoFromAvailableCryptoActivity(UsersDao usersDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public GetCryptoFromAvailableCryptoResult handleRequest(
            final GetCryptoFromAvailableCryptoRequest getCryptoFromAvailableCryptoRequest, Context context) {

        if (getCryptoFromAvailableCryptoRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (getCryptoFromAvailableCryptoRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(getCryptoFromAvailableCryptoRequest.getToken());

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

        // try to find crypto
        CryptoCurrencies findedCrypto = cryptoDao.getCrypto(getCryptoFromAvailableCryptoRequest.getCryptoName());
        if (findedCrypto == null) {
            throw new CryptoNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Crypto doesn't exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        return GetCryptoFromAvailableCryptoResult.builder()
                .withCryptoName(findedCrypto.getCryptoName())
                .withImage("")
                .withImageUrl("")
                .withCryptoDescription(findedCrypto.getCryptoDescription())
                .withCryptoAmount(findedCrypto.getCryptoAmount())
                .withCryptoCost(findedCrypto.getCryptoCost())
                .build();
    }
}
