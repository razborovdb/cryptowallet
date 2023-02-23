package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToWalletRequest;
import com.development.models.requests.AddWalletRequest;
import com.development.models.results.AddCryptoToWalletResult;
import com.development.models.results.AddWalletResult;
import com.development.util.DaggerService;

public class AddCryptoToWalletActivityProvider implements RequestHandler<AddCryptoToWalletRequest, AddCryptoToWalletResult> {
    @Override
    public AddCryptoToWalletResult handleRequest(AddCryptoToWalletRequest addCryptoToWalletRequest, Context context) {

        return getDagger().provideAddCryptoToWalletActivity().handleRequest(addCryptoToWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
