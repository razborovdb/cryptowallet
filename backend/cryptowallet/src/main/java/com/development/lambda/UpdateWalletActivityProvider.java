package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddWalletRequest;
import com.development.models.requests.UpdateWalletRequest;
import com.development.models.results.AddWalletResult;
import com.development.models.results.UpdateWalletResult;
import com.development.util.DaggerService;

public class UpdateWalletActivityProvider implements RequestHandler<UpdateWalletRequest, UpdateWalletResult> {
    @Override
    public UpdateWalletResult handleRequest(UpdateWalletRequest updateWalletRequest, Context context) {

        return getDagger().provideUpdateWalletActivity().handleRequest(updateWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
