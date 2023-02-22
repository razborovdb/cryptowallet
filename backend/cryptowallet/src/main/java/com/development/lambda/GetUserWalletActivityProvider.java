package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddWalletRequest;
import com.development.models.requests.GetUserWalletRequest;
import com.development.models.results.AddWalletResult;
import com.development.models.results.GetUserWalletResult;
import com.development.util.DaggerService;

public class GetUserWalletActivityProvider implements RequestHandler<GetUserWalletRequest, GetUserWalletResult> {
    @Override
    public GetUserWalletResult handleRequest(GetUserWalletRequest getUserWalletRequest, Context context) {

        return getDagger().provideGetUserWalletActivity().handleRequest(getUserWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
