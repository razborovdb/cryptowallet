package com.development.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.development.dynamodb.ProjectsDao;
import com.development.dynamodb.models.Projects;
import com.development.models.ProjectsModel;
import com.development.models.requests.GetProjectsRequest;
import com.development.models.results.GetProjectsResult;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProjectsActivity implements RequestHandler<GetProjectsRequest, GetProjectsResult> {

    private final ProjectsDao projectsDao;
    private String defaultProjectName = "Crypto Wallet Service";

    /**
     * GetProjectsActivity.
     *
     * @param projectsDao - projectsDao
     */
    @Inject
    public GetProjectsActivity(ProjectsDao projectsDao) {
        this.projectsDao = projectsDao;
    }


    @Override
    public GetProjectsResult handleRequest(
            final GetProjectsRequest getAvailableCryptocurrenciesRequest, Context context) {
        Projects projects = projectsDao.getProjects(defaultProjectName);

        System.out.println(projects);

        return GetProjectsResult.builder()
                .withProjectName(projects.getProjectName())
                .withProjectDescription(projects.getProjectDescription())
                .withImage(projects.getImage())
                .withImageUrl(projects.getImageUrl())
                .withProjectCost(projects.getProjectCost())
                .build();
    }

}
