package com.development.models.requests;

import java.util.Objects;

public class DeleteUserWalletRequest {
    private String token;

    private String email;
    private String walletName;


    public DeleteUserWalletRequest() {

    }

    public DeleteUserWalletRequest(Builder builder) {

        this.token = builder.token;
        this.email = builder.email;
        this.walletName = builder.walletName;

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

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteUserWalletRequest that = (DeleteUserWalletRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(walletName, that.walletName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, walletName);
    }

    @Override
    public String toString() {
        return "DeleteUserWalletRequest{" +
                "token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", walletName='" + walletName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;
        private String email;
        private String walletName;

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

        public Builder withWalletName(String useWalletName) {
            this.walletName = useWalletName;
            return this;
        }



        public DeleteUserWalletRequest build() {
            return new DeleteUserWalletRequest(this);
        }
    }
}
