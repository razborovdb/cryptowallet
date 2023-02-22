package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.AddWalletRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.AddWalletResult;
import com.development.util.DaggerService;

public class AddWalletActivityProvider implements RequestHandler<AddWalletRequest, AddWalletResult> {
    @Override
    public AddWalletResult handleRequest(AddWalletRequest addWalletRequest, Context context) {

        return getDagger().provideAddWalletActivity().handleRequest(addWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
