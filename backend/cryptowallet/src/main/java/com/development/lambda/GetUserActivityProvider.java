package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.GetUserRequest;
import com.development.models.results.GetUserResult;
import com.development.util.DaggerService;

public class GetUserActivityProvider implements RequestHandler<GetUserRequest, GetUserResult> {
    @Override
    public GetUserResult handleRequest(GetUserRequest getUserRequest, Context context) {

        return getDagger().provideGetUserActivity().handleRequest(getUserRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }

}
