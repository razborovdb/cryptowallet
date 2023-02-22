package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.util.DaggerService;

public class AddCryptoToAvailableCryptoActivityProvider implements RequestHandler<AddCryptoToAvailableCryptoRequest, AddCryptoToAvailableCryptoResult> {
    @Override
    public AddCryptoToAvailableCryptoResult handleRequest(AddCryptoToAvailableCryptoRequest addCryptoToAvailableCryptoRequest, Context context) {

        return getDagger().provideAddCryptoToAvailableCryptoActivity().handleRequest(addCryptoToAvailableCryptoRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
