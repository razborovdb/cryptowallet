package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.CryptoCurrencies;
import com.development.dynamodb.models.Users;
import com.development.env.EnvironmentVariable;
import com.development.exceptions.BadRequestException;
import com.development.exceptions.BadTokenException;
import com.development.exceptions.ErrorMessage;
import com.development.models.requests.GetAvailableCryptoRequest;
import com.development.models.requests.UpdateAvailableCryptoRequest;
import com.development.models.results.GetAvailableCryptoResult;
import com.development.models.results.UpdateAvailableCryptoResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateAvailableCryptoActivity implements RequestHandler<UpdateAvailableCryptoRequest, UpdateAvailableCryptoResult> {
    private final UsersDao usersDao;
    private final CryptoDao cryptoDao;

    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public UpdateAvailableCryptoActivity(CryptoDao cryptoDao, UsersDao usersDao) {
        this.cryptoDao = cryptoDao;
        this.usersDao = usersDao;
    }

    @Override
    public UpdateAvailableCryptoResult handleRequest(
            final UpdateAvailableCryptoRequest updateAvailableCryptocurrenciesRequest, Context context) {

        if (updateAvailableCryptocurrenciesRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }

        if (updateAvailableCryptocurrenciesRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(updateAvailableCryptocurrenciesRequest.getToken());
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

        StringBuffer response = new StringBuffer();
        try {
            URL urlForGetRequest = new URL("http://api.coinlayer.com/live?access_key=" + EnvironmentVariable.CRYPTO_ACCESS_KEY);
            ;
            String readLine = null;
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();;
            conection.setRequestMethod("GET");

            int responseCode = conection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));;



                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                // print result
                //System.out.println("JSON String Result " + response.toString());
                //GetAndPost.POSTRequest(response.toString());
            } else {
                System.out.println("GET NOT WORKED");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String cryptoValue = response.toString();

        int i = cryptoValue.indexOf("rates");
        if (i > 0) {
            cryptoValue = cryptoValue.substring(i+8, cryptoValue.length() - 2);
        }
        String[] cryptoList = cryptoValue.split(",");
        Map<String, Double> cryptoMap = new HashMap<>();

        for (String s: cryptoList) {
            String[] spl = s.split(":");
            cryptoMap.put(spl[0].replaceAll("\"",""), Double.parseDouble(spl[1]));
        }

        for (CryptoCurrencies crypto : cryptocurrencyList) {
            if (cryptoMap.containsKey(crypto.getCryptoName())) {
                crypto.setCryptoCost(cryptoMap.get(crypto.getCryptoName()));
                cryptoDao.updateCrypto(crypto);
            }
        }

        cryptocurrencyList = cryptoDao.getAllCryptocurrency();

        return UpdateAvailableCryptoResult.builder()
                .withCryptocurrencyList(cryptocurrencyList)
                .build();
    }
}
