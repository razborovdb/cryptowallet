package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import com.development.dynamodb.models.Projects;

import javax.inject.Inject;


public class ProjectsDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * CryptocurrencyDao.
     * @param dynamoDbMapper - DynamoDBMapper
     */
    @Inject
    public ProjectsDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * getProjects.
     * @return projectsList
     */
    public Projects getProjects(String projectName) {
        Projects projects = new Projects();
        projects.setProjectName(projectName);

        System.out.println("-------------------- project name ------------------------");
        System.out.println(projects.getProjectName());

        DynamoDBQueryExpression<Projects> queryExpression = new DynamoDBQueryExpression<Projects>()
                .withHashKeyValues(projects);
        System.out.println("-------------------- query ------------------------");
        System.out.println(queryExpression);

        try {
            PaginatedQueryList<Projects> projectsList = dynamoDbMapper.query(Projects.class, queryExpression);


            if (projectsList.size() > 0) {
                return projectsList.get(0);
            }
        } catch (Exception e) {

        }



        return new Projects();
    }
}
