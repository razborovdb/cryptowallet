package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.AddCryptoToAvailableCryptoRequest;
import com.development.models.requests.DeleteCryptoInAvailableCryptoRequest;
import com.development.models.results.AddCryptoToAvailableCryptoResult;
import com.development.models.results.DeleteCryptoInAvailableCryptoResult;
import com.development.util.DaggerService;

public class DeleteCryptoInAvailableCryptoActivityProvider implements RequestHandler<DeleteCryptoInAvailableCryptoRequest, DeleteCryptoInAvailableCryptoResult> {
    @Override
    public DeleteCryptoInAvailableCryptoResult handleRequest(DeleteCryptoInAvailableCryptoRequest deleteCryptoInAvailableCryptoRequest, Context context) {

        return getDagger().provideDeleteCryptoInAvailableCryptoActivity().handleRequest(deleteCryptoInAvailableCryptoRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
