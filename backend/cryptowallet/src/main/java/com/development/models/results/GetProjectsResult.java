package com.development.models.results;

import com.development.dynamodb.models.Projects;


public class GetProjectsResult {
    private String projectName;
    private String projectDescription;
    private String image;
    private String imageUrl;
    private double projectCost;

    public GetProjectsResult() {

    }

    public GetProjectsResult(Builder builder) {
        this.projectName = builder.projectName;
        this.projectDescription = builder.projectDescription;
        this.image = builder.image;
        this.imageUrl = builder.imageUrl;
        this.projectCost = builder.projectCost;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(double projectCost) {
        this.projectCost = projectCost;
    }

    @Override
    public String toString() {
        return "GetProjectsResult{" +
                "projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", projectCost=" + projectCost +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String projectName;
        private String projectDescription;
        private String image;
        private String imageUrl;
        private double projectCost;
        private Builder() {

        }


        public Builder withProjectName(String useProjectName) {
            this.projectName = useProjectName;
            return this;
        }

        public Builder withProjectDescription(String useProjectDescription) {
            this.projectDescription = useProjectDescription;
            return this;
        }

        public Builder withImage(String useImage) {
            this.image = useImage;
            return this;
        }

        public Builder withImageUrl(String useImageUrl) {
            this.imageUrl = useImageUrl;
            return this;
        }

        public Builder withProjectCost(double useProjectCost) {
            this.projectCost = useProjectCost;
            return this;
        }

        public GetProjectsResult build() {
            return new GetProjectsResult(this);
        }
    }
}
