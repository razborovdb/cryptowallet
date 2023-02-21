package com.development.models.requests;


import java.util.Objects;

public class GetUserRequest {
    private String token;
    private String email;


    public GetUserRequest() {

    }

    public GetUserRequest(Builder builder) {
        this.token = builder.token;
        this.email = builder.email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetUserRequest that = (GetUserRequest) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "token='" + token + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;
        private String email;

        private Builder() {

        }

        public Builder withToken(String useToken) {
            this.token = useToken;
            return this;
        }

        public Builder withEmail(String useEmail) {
            this.email = useEmail;
            return this;
        }

        public GetUserRequest build() {
            return new GetUserRequest(this);
        }
    }
}
