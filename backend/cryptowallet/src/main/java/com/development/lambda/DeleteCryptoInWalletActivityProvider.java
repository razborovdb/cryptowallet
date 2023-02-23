package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.DeleteCryptoInWalletRequest;
import com.development.models.requests.UpdateCryptoInWalletRequest;
import com.development.models.results.DeleteCryptoInWalletResult;
import com.development.models.results.UpdateCryptoInWalletResult;
import com.development.util.DaggerService;

public class DeleteCryptoInWalletActivityProvider implements RequestHandler<DeleteCryptoInWalletRequest, DeleteCryptoInWalletResult> {
    @Override
    public DeleteCryptoInWalletResult handleRequest(DeleteCryptoInWalletRequest deleteCryptoInWalletRequest, Context context) {

        return getDagger().provideDeleteCryptoInWalletActivity().handleRequest(deleteCryptoInWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
