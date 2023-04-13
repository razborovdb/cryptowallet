package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.UpdateUserRequest;
import com.development.models.results.UpdateUserResult;
import com.development.util.DaggerService;

public class UpdateUserActivityProvider implements RequestHandler<UpdateUserRequest, UpdateUserResult>  {
    @Override
    public UpdateUserResult handleRequest(UpdateUserRequest editUserRequest, Context context) {

        return getDagger().provideUpdateUserActivity().handleRequest(editUserRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }

}
