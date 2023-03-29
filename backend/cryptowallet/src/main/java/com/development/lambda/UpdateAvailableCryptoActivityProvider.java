package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.GetAvailableCryptoRequest;
import com.development.models.requests.UpdateAvailableCryptoRequest;
import com.development.models.results.GetAvailableCryptoResult;
import com.development.models.results.UpdateAvailableCryptoResult;
import com.development.util.DaggerService;

public class UpdateAvailableCryptoActivityProvider implements RequestHandler<UpdateAvailableCryptoRequest, UpdateAvailableCryptoResult> {
    public UpdateAvailableCryptoActivityProvider()
    {

    }

    @Override
    public UpdateAvailableCryptoResult handleRequest(UpdateAvailableCryptoRequest updateAvailableCryptoRequest, Context context) {
        return getDagger().provideUpdateAvailableCryptoActivity().handleRequest(updateAvailableCryptoRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
