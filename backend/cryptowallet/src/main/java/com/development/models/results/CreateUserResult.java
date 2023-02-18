package com.development.models.results;

import com.development.dynamodb.models.Projects;

public class CreateUserResult {
    private String token;

    public CreateUserResult(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CreateUserResult{" +
                "token='" + token + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;
        private Builder() {

        }

        public Builder withToken(String useToken) {
            this.token = useToken;
            return this;
        }


        public CreateUserResult build() {
            return new CreateUserResult(this);
        }
    }
}
