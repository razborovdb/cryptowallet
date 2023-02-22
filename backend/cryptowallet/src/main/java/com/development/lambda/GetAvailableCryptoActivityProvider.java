package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.GetAvailableCryptoRequest;
import com.development.models.results.GetAvailableCryptoResult;
import com.development.util.DaggerService;

public class GetAvailableCryptoActivityProvider implements RequestHandler<GetAvailableCryptoRequest, GetAvailableCryptoResult> {
    public GetAvailableCryptoActivityProvider()
    {

    }

    @Override
    public GetAvailableCryptoResult handleRequest(GetAvailableCryptoRequest getAvailableCryptoRequest, Context context) {
        return getDagger().provideGetAvailableCryptoActivity().handleRequest(getAvailableCryptoRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
