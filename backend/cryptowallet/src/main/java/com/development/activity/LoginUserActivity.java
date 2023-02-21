package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.Users;
import com.development.exceptions.BadRequestException;
import com.development.exceptions.BadUserNameOrPasswordException;
import com.development.exceptions.ErrorMessage;
import com.development.exceptions.ServerErrorException;
import com.development.models.requests.LoginUserRequest;
import com.development.models.results.LoginUserResult;
import com.development.util.JsonWebToken;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.Optional;

public class LoginUserActivity implements RequestHandler<LoginUserRequest, LoginUserResult> {
    private final UsersDao usersDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public LoginUserActivity(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @Override
    public LoginUserResult handleRequest(
            final LoginUserRequest loginUserRequest, Context context) {

        if (loginUserRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (loginUserRequest.getEmail() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        Users user = usersDao.getUser(loginUserRequest.getEmail());

        if (user == null) {
            throw new BadUserNameOrPasswordException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad user name or password ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // Check password
        if (!BCrypt.checkpw(loginUserRequest.getPassword(), user.getPassword())) {
            throw new BadUserNameOrPasswordException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad user name or password ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        user.setPassword("");

        // create web token string
        Optional<String> jwts = jsonWebToken.genJsonWebToken(user);

        if (jwts.isEmpty()) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        return LoginUserResult.builder()
                .withToken(jwts.get())
                .build();
    }

}
