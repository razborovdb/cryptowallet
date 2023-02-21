package com.development.models.results;

public class LoginUserResult {
    private String token;

    public LoginUserResult() {

    }

    public LoginUserResult(Builder builder) {
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
        return "LoginUserResult{" +
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


        public LoginUserResult build() {
            return new LoginUserResult(this);
        }
    }
}
