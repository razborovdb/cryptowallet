package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.GetCryptoFromAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.GetCryptoFromAvailableCryptoResult;
import com.development.util.DaggerService;

public class GetCryptoFromAvailableCryptoActivityProvider implements RequestHandler<GetCryptoFromAvailableCryptoRequest, GetCryptoFromAvailableCryptoResult> {
    @Override
    public GetCryptoFromAvailableCryptoResult handleRequest(GetCryptoFromAvailableCryptoRequest getCryptoFromAvailableCryptoRequest, Context context) {

        return getDagger().provideGetCryptoFromAvailableCryptoActivity().handleRequest(getCryptoFromAvailableCryptoRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
