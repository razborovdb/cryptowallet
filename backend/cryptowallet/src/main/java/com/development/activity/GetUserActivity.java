package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.Users;
import com.development.exceptions.*;
import com.development.models.requests.GetUserRequest;
import com.development.models.results.GetUserResult;
import com.development.util.JsonWebToken;

import javax.inject.Inject;

public class GetUserActivity implements RequestHandler<GetUserRequest, GetUserResult> {

    private final UsersDao usersDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public GetUserActivity(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public GetUserResult handleRequest(
            final GetUserRequest getUserRequest, Context context) {

        if (getUserRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (getUserRequest.getEmail() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        if (getUserRequest.getToken() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // check token
        Users user = jsonWebToken.getUserInformationFromToken(getUserRequest.getToken());

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
        if (!(user.getAdmin() || user.getEmail().equals(getUserRequest.getEmail()))) {
            throw new NotAuthorizedException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Not authorized ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // try to find user
        Users findedUser = usersDao.getUser(getUserRequest.getEmail());
        if (findedUser == null) {
            throw new UserNotExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("User didn't find ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }
        findedUser.setPassword("");
        if (findedUser.getAvatar() == null) {
            findedUser.setAvatar("");
        }
        if (findedUser.getAvatarUrl() == null) {
            findedUser.setAvatarUrl("");
        }
        return GetUserResult.builder()
                .withEmail(findedUser.getEmail())
                .withName(findedUser.getName())
                .withAvatar(findedUser.getAvatar())
                .withAvatarUrl(findedUser.getAvatarUrl())
                .withPassword(findedUser.getPassword())
                .withRole(findedUser.getRole())
                .withIsAdmin(findedUser.getAdmin())
                .build();



    }


}
