package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.Users;
import com.development.exceptions.*;
import com.development.models.requests.UpdateUserRequest;
import com.development.models.results.UpdateUserResult;
import com.development.util.CloudinaryImages;
import com.development.util.JsonWebToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.Map;


public class UpdateUserActivity implements RequestHandler<UpdateUserRequest, UpdateUserResult> {

    private final UsersDao usersDao;
    JsonWebToken jsonWebToken = new JsonWebToken();

    CloudinaryImages cloudinaryImages = new CloudinaryImages();


    @Inject
    public UpdateUserActivity(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @Override
    public UpdateUserResult handleRequest(
            final UpdateUserRequest editUserRequest, Context context) {

        if (editUserRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (editUserRequest.getEmail() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        if (editUserRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(editUserRequest.getToken());

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
        if (!(user.getAdmin() || user.getEmail().equals(editUserRequest.getEmail()))) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        user = usersDao.getUser(editUserRequest.getEmail());

        if (user == null) {
            throw new UserNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("User doesn't exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        //-----------------------------------------------
        if (editUserRequest.getAvatarUrl() != null) {
            if (!editUserRequest.getAvatarUrl().equals("")) {
                Cloudinary cloudinary = cloudinaryImages.getCloudinary();
                try {
                    if(user.getAvatar() != null) {
                        if (!user.getAvatar().isEmpty()) {
                            Map destroyRsponse = cloudinary.uploader().destroy(user.getAvatar(), ObjectUtils.asMap());
                        }
                    }
                } catch (Exception e) {

                }
                try {

                    Map uploadResult = cloudinary.uploader().upload(editUserRequest.getAvatarUrl(),
                            ObjectUtils.asMap(
                                    "upload_preset", "cryptowallet"
                            ));


                    String json = new ObjectMapper().writeValueAsString(uploadResult);


                    String image = "";
                    if (uploadResult.containsKey("public_id")) {
                        image = uploadResult.get("public_id").toString();
                    }
                    user.setAvatar(image);

                    String imageUrl = "";
                    if (uploadResult.containsKey("url")) {
                        imageUrl = uploadResult.get("url").toString();
                    }

                    user.setAvatarUrl(imageUrl);

                } catch (Exception e) {

                }

            }

        }


        //-------------------------------------------------


        Users newUser = new Users(user.getEmail(), user.getName(),  user.getAvatar(),
                user.getAvatarUrl(), user.getPassword(), user.getRole(), user.getAdmin());


        // Save newUser to database
        Users updatedUser = usersDao.updateUser(newUser);
        updatedUser.setPassword("");
        //updatedUser.setAvatar("");
        //updatedUser.setAvatarUrl("");


        return UpdateUserResult.builder()
                .withEmail(updatedUser.getEmail())
                .withName(updatedUser.getName())
                .withAvatar(updatedUser.getAvatar())
                .withAvatarUrl(updatedUser.getAvatarUrl())
                .withPassword(updatedUser.getPassword())
                .withRole(updatedUser.getRole())
                .withIsAdmin(updatedUser.getAdmin())
                .build();
    }

}
