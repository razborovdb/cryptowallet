package com.development.models.results;

import com.development.dynamodb.models.Projects;
import com.development.models.ProjectsModel;
import lombok.*;

import java.util.List;


public class GetProjectsResult {
    private Projects project;

    public GetProjectsResult(Builder builder) {
        this.project = builder.project;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "GetProjectsResult{" +
                "project=" + project +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Projects project;
        private Builder() {

        }

        public Builder withProject(Projects useProject) {
            this.project = useProject;
            return this;
        }

        public GetProjectsResult build() {
            return new GetProjectsResult(this);
        }
    }
}
