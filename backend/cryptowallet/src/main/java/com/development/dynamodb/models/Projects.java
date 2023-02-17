package com.development.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;


@DynamoDBTable(tableName = "projects")
public class Projects {

    private String projectName;

    private String projectDescription;

    private String image;

    private String imageUrl;

    private double projectCost;

    @DynamoDBHashKey(attributeName = "projectname")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @DynamoDBAttribute(attributeName = "projectdescription")
    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    @DynamoDBAttribute(attributeName = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @DynamoDBAttribute(attributeName = "imageurl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @DynamoDBAttribute(attributeName = "projectcost")
    public double getProjectCost() {
        return projectCost;
    }


    public void setProjectCost(double projectCost) {
        this.projectCost = projectCost;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", projectCost=" + projectCost +
                '}';
    }
}
