package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.CreateUserRequest;
import com.development.models.results.CreateUserResult;
import com.development.util.DaggerService;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult>  {
    @Override
    public CreateUserResult handleRequest(CreateUserRequest createUserRequest, Context context) {

        return getDagger().provideCreateUserActivity().handleRequest(createUserRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }

}
