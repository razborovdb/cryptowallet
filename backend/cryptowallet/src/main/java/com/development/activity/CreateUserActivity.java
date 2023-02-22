package com.development.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.models.Users;
import com.development.exceptions.BadRequestException;
import com.development.exceptions.ErrorMessage;
import com.development.exceptions.ServerErrorException;
import com.development.exceptions.UserAlreadyExistException;
import com.development.models.requests.CreateUserRequest;

import com.development.models.results.CreateUserResult;
import com.development.util.JsonWebToken;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.Optional;


public class CreateUserActivity implements RequestHandler<CreateUserRequest, CreateUserResult> {

    private final UsersDao usersDao;
    JsonWebToken jsonWebToken = new JsonWebToken();


    @Inject
    public CreateUserActivity(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @Override
    public CreateUserResult handleRequest(
            final CreateUserRequest createUserRequest, Context context) {

        if (createUserRequest == null) {

            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));

        }
        if (createUserRequest.getEmail() == null) {
            throw new BadRequestException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("Bad request ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        Users user = usersDao.getUser(createUserRequest.getEmail());

        if (user != null) {
            throw new UserAlreadyExistException((ErrorMessage.builder()
                    .withStatus(400)
                    .withError("User already exist ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        // Encode password
        String encrypted = BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt(10));

                BCrypt.checkpw(createUserRequest.getPassword(), encrypted);



        Users newUser = new Users(createUserRequest.getEmail(), createUserRequest.getName(),  createUserRequest.getAvatar(),
                createUserRequest.getAvatarUrl(), encrypted, createUserRequest.getRole(), createUserRequest.getIsAdmin());


        // Save newUser to database
        Users createdUser = usersDao.createUser(newUser);
        createdUser.setPassword("");
        createdUser.setAvatar("");
        createdUser.setAvatarUrl("");

        // create web token string
        Optional<String> jwts = jsonWebToken.genJsonWebToken(createdUser);

        if (jwts.isEmpty()) {
            throw new ServerErrorException((ErrorMessage.builder()
                    .withStatus(500)
                    .withError("Internal server error ...")
                    .withContentType("application/json")
                    .build().toString()
            ));
        }

        return CreateUserResult.builder()
                .withToken(jwts.get())
                .build();
    }

}
