package com.development.models.requests;

public class GetProjectsRequest {

    public GetProjectsRequest() {

    }

    public GetProjectsRequest(Builder builder) {

    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Builder() {

        }


        public GetProjectsRequest build() {
            return new GetProjectsRequest(this);
        }
    }
}
