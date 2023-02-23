package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.DeleteUserWalletRequest;
import com.development.models.requests.GetUserOneWalletRequest;
import com.development.models.results.DeleteUserWalletResult;
import com.development.models.results.GetUserOneWalletResult;
import com.development.util.DaggerService;

public class DeleteUserWalletActivityProvider implements RequestHandler<DeleteUserWalletRequest, DeleteUserWalletResult> {
    @Override
    public DeleteUserWalletResult handleRequest(DeleteUserWalletRequest deleteUserWalletRequest, Context context) {

        return getDagger().provideDeleteUserWalletActivity().handleRequest(deleteUserWalletRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }
}
