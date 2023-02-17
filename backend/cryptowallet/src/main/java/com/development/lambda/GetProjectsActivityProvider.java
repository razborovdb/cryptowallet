package com.development.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dependency.ServiceComponent;
import com.development.models.requests.GetProjectsRequest;
import com.development.models.results.GetProjectsResult;
import com.development.util.DaggerService;

public class GetProjectsActivityProvider implements RequestHandler<GetProjectsRequest, GetProjectsResult> {
    /**
     * GetAllWalletsActivityProvider.
     */
    public GetProjectsActivityProvider()
    {

    }

    /**
     * handleRequest.
     * @param getProjectsRequest The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return - Projects or null
     */
    @Override
    public GetProjectsResult handleRequest(GetProjectsRequest getProjectsRequest, Context context) {
        return getDagger().provideGetProjectsActivity().handleRequest(getProjectsRequest, context);
    }

    private ServiceComponent getDagger() {
        return DaggerService.getDagger();
    }


}
