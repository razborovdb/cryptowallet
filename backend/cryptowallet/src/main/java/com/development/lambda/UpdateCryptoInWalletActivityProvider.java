package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToWalletRequest;
import com.development.models.requests.UpdateCryptoInWalletRequest;
import com.development.models.results.AddCryptoToWalletResult;
import com.development.models.results.UpdateCryptoInWalletResult;
import com.development.util.DaggerService;

public class UpdateCryptoInWalletActivityProvider implements RequestHandler<UpdateCryptoInWalletRequest, UpdateCryptoInWalletResult> {
    @Override
    public UpdateCryptoInWalletResult handleRequest(UpdateCryptoInWalletRequest updateCryptoInWalletRequest, Context context) {

        return getDagger().provideUpdateCryptoInWalletActivity().handleRequest(updateCryptoInWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
