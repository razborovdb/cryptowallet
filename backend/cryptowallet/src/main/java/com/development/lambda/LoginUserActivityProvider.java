package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.LoginUserRequest;
import com.development.models.results.LoginUserResult;
import com.development.util.DaggerService;

public class LoginUserActivityProvider implements RequestHandler<LoginUserRequest, LoginUserResult> {
    @Override
    public LoginUserResult handleRequest(LoginUserRequest loginUserRequest, Context context) {

        return getDagger().provideLoginUserActivity().handleRequest(loginUserRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
