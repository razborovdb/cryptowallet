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
import com.development.models.requests.UpdateCryptoInAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.UpdateCryptoInAvailableCryptoResult;
import com.development.util.CloudinaryImages;
import com.development.util.JsonWebToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.Map;

public class UpdateCryptoInAvailableCryptoActivity implements RequestHandler<UpdateCryptoInAvailableCryptoRequest, UpdateCryptoInAvailableCryptoResult> {
    private final UsersDao usersDao;
    private final CryptoDao cryptoDao;
    JsonWebToken jsonWebToken = new JsonWebToken();
    CloudinaryImages cloudinaryImages = new CloudinaryImages();


    @Inject
    public UpdateCryptoInAvailableCryptoActivity(UsersDao usersDao, CryptoDao cryptoDao) {
        this.usersDao = usersDao;
        this.cryptoDao = cryptoDao;
    }


    @Override
    public UpdateCryptoInAvailableCryptoResult handleRequest(
            final UpdateCryptoInAvailableCryptoRequest updateCryptoInAvailableCryptoRequest, Context context) {
//        LambdaLogger logger = context.getLogger();
//
//        logger.log("----------------------------------------------------------- ");
//        logger.log(updateCryptoInAvailableCryptoRequest.toString());
//        logger.log("----------------------------------------------------------- ");
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
        if (!(user.getAdmin())) {
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
        //-----------------------------------------------
        if (updateCryptoInAvailableCryptoRequest.getImageUrl() != null) {
            if (!updateCryptoInAvailableCryptoRequest.getImageUrl().equals("")) {
                Cloudinary cloudinary = cloudinaryImages.getCloudinary();
                try {
                    if (updateCryptoInAvailableCryptoRequest.getImage() != null) {
                        if (!updateCryptoInAvailableCryptoRequest.getImage().isEmpty()) {
                            Map destroyRsponse = cloudinary.uploader().destroy(updateCryptoInAvailableCryptoRequest.getImage(), ObjectUtils.asMap());
                        }
                    }
                } catch (Exception e) {

//                        cryptoDetails.setImage("");
//                        cryptoDetails.setImageUrl("");

                }
                try {

                    Map uploadResult = cloudinary.uploader().upload(updateCryptoInAvailableCryptoRequest.getImageUrl(),
                            ObjectUtils.asMap(
                                    "upload_preset", "cryptowallet"
                            ));


                    String json = new ObjectMapper().writeValueAsString(uploadResult);


                    String image = "";
                    if (uploadResult.containsKey("public_id")) {
                        image = uploadResult.get("public_id").toString();
                    }
                    updateCryptoInAvailableCryptoRequest.setImage(image);

                    String imageUrl = "";
                    if (uploadResult.containsKey("url")) {
                        imageUrl = uploadResult.get("url").toString();
                    }

                    updateCryptoInAvailableCryptoRequest.setImageUrl(imageUrl);

                } catch (Exception e) {

//                        cryptoDetails.setImage("");
//                        cryptoDetails.setImageUrl("");

                }
                findedCrypto.setImage(updateCryptoInAvailableCryptoRequest.getImage());
                findedCrypto.setImageUrl(updateCryptoInAvailableCryptoRequest.getImageUrl());

            }

        }


        //-------------------------------------------------


        CryptoCurrencies newCrypto = new CryptoCurrencies();
        newCrypto.setCryptoName(findedCrypto.getCryptoName());
        newCrypto.setImage(findedCrypto.getImage());
        newCrypto.setImageUrl(findedCrypto.getImageUrl());
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
                .withImage(updatedCrypto.getImage())
                .withImageUrl(updatedCrypto.getImageUrl())
                .withCryptoDescription(updatedCrypto.getCryptoDescription())
                .withCryptoAmount(updatedCrypto.getCryptoAmount())
                .withCryptoCost(updatedCrypto.getCryptoCost())
                .build();
    }
}
