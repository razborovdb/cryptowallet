package com.development.models.requests;

import java.util.Objects;

public class LoginUserRequest {
    private String email;
    private String password;


    public LoginUserRequest() {

    }

    public LoginUserRequest(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginUserRequest that = (LoginUserRequest) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String email;
        private String password;
        private Builder() {

        }

        public Builder withEmail(String useEmail) {
            this.email = useEmail;
            return this;
        }

        public Builder withPassword(String usePassword) {
            this.password = usePassword;
            return this;
        }

        public LoginUserRequest build() {
            return new LoginUserRequest(this);
        }
    }
}
