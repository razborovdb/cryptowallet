package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.CryptoCurrencies;
import com.development.dynamodb.models.Users;
import com.development.exceptions.*;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.util.CloudinaryImages;
import com.development.util.JsonWebToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.Map;

public class AddCryptoToAvailableCryptoActivity implements RequestHandler<AddCryptoToAvailableCryptoRequest, AddCryptoToAvailableCryptoResult>  {
    private final UsersDao usersDao;
    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();

    CloudinaryImages cloudinaryImages = new CloudinaryImages();


    @Inject
    public AddCryptoToAvailableCryptoActivity(UsersDao usersDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public AddCryptoToAvailableCryptoResult handleRequest(
            final AddCryptoToAvailableCryptoRequest addCryptoToAvailableCryptoRequest, Context context) {

//        LambdaLogger logger = context.getLogger();
//
//        logger.log("----------------------------------------------------------- ");
//        logger.log(addCryptoToAvailableCryptoRequest.toString());
//        logger.log("----------------------------------------------------------- ");

        if (addCryptoToAvailableCryptoRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (addCryptoToAvailableCryptoRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(addCryptoToAvailableCryptoRequest.getToken());

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
        CryptoCurrencies findedCrypto = cryptoDao.getCrypto(addCryptoToAvailableCryptoRequest.getCryptoName());
        if (findedCrypto != null) {
            throw new CryptoAlreadyExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Crypto already exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // ---------------------------------------------------------------------------------------------------
        if (addCryptoToAvailableCryptoRequest.getImageUrl() != null) {


            Cloudinary cloudinary = cloudinaryImages.getCloudinary();
            //

            try {
                Map uploadResult = cloudinary.uploader().upload(addCryptoToAvailableCryptoRequest.getImageUrl(),  ObjectUtils.asMap(
                        "upload_preset", "cryptowallet"
                ));


                String json = new ObjectMapper().writeValueAsString(uploadResult);

                String image = "";
                if (uploadResult.containsKey("public_id")) {
                    image = uploadResult.get("public_id").toString();
                }
                addCryptoToAvailableCryptoRequest.setImage(image);

                String imageUrl = "";
                if (uploadResult.containsKey("url")) {
                    imageUrl = uploadResult.get("url").toString();
                }

                addCryptoToAvailableCryptoRequest.setImageUrl(imageUrl);

            } catch (Exception e) {
                addCryptoToAvailableCryptoRequest.setImage("");
                addCryptoToAvailableCryptoRequest.setImageUrl("");

            }

        }

        // --------------------------------------------------------------------------------------------------
        CryptoCurrencies newCrypto = new CryptoCurrencies();
        newCrypto.setCryptoName(addCryptoToAvailableCryptoRequest.getCryptoName());
        newCrypto.setCryptoDescription(addCryptoToAvailableCryptoRequest.getCryptoDescription());
        newCrypto.setImage(addCryptoToAvailableCryptoRequest.getImage());
        newCrypto.setImageUrl(addCryptoToAvailableCryptoRequest.getImageUrl());
        newCrypto.setCryptoAmount(addCryptoToAvailableCryptoRequest.getCryptoAmount());
        newCrypto.setCryptoCost(addCryptoToAvailableCryptoRequest.getCryptoCost());

        CryptoCurrencies addedCrypto = cryptoDao.createCrypto(newCrypto);
        if (addedCrypto == null) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        return AddCryptoToAvailableCryptoResult.builder()
                .withCryptoName(addedCrypto.getCryptoName())
                .withImage("")
                .withImageUrl("")
                .withCryptoDescription(addedCrypto.getCryptoDescription())
                .withCryptoAmount(addedCrypto.getCryptoAmount())
                .withCryptoCost(addedCrypto.getCryptoCost())
                .build();
    }
}
