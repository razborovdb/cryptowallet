package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.CryptoCurrencies;
import com.development.dynamodb.models.Users;
import com.development.exceptions.BadRequestException;
import com.development.exceptions.BadTokenException;
import com.development.exceptions.ErrorMessage;
import com.development.models.requests.GetAvailableCryptoRequest;
import com.development.models.results.GetAvailableCryptoResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.util.List;

public class GetAvailableCryptoActivity implements RequestHandler<GetAvailableCryptoRequest, GetAvailableCryptoResult> {
    private final UsersDao usersDao;
    private final CryptoDao cryptoDao;

    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public GetAvailableCryptoActivity(CryptoDao cryptoDao, UsersDao usersDao) {
        this.cryptoDao = cryptoDao;
        this.usersDao = usersDao;
    }

    @Override
    public GetAvailableCryptoResult handleRequest(
            final GetAvailableCryptoRequest getAvailableCryptocurrenciesRequest, Context context) {

        if (getAvailableCryptocurrenciesRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }

        if (getAvailableCryptocurrenciesRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(getAvailableCryptocurrenciesRequest.getToken());
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

        List<CryptoCurrencies> cryptocurrencyList = cryptoDao.getAllCryptocurrency();

        return GetAvailableCryptoResult.builder()
                .withCryptocurrencyList(cryptocurrencyList)
                .build();
    }
}
