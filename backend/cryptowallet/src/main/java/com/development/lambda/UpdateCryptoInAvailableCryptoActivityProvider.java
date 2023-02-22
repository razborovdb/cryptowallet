package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.UpdateCryptoInAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.UpdateCryptoInAvailableCryptoResult;
import com.development.util.DaggerService;

public class UpdateCryptoInAvailableCryptoActivityProvider implements RequestHandler<UpdateCryptoInAvailableCryptoRequest, UpdateCryptoInAvailableCryptoResult> {
    @Override
    public UpdateCryptoInAvailableCryptoResult handleRequest(UpdateCryptoInAvailableCryptoRequest updateCryptoInAvailableCryptoRequest, Context context) {

        return getDagger().provideUpdateCryptoInAvailableCryptoActivity().handleRequest(updateCryptoInAvailableCryptoRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
