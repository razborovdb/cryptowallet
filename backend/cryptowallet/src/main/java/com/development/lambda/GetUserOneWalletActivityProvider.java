package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.GetUserOneWalletRequest;
import com.development.models.requests.GetUserWalletRequest;
import com.development.models.results.GetUserOneWalletResult;
import com.development.models.results.GetUserWalletResult;
import com.development.util.DaggerService;

public class GetUserOneWalletActivityProvider implements RequestHandler<GetUserOneWalletRequest, GetUserOneWalletResult> {
    @Override
    public GetUserOneWalletResult handleRequest(GetUserOneWalletRequest getUserOneWalletRequest, Context context) {

        return getDagger().provideGetUserOneWalletActivity().handleRequest(getUserOneWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
