package com.development.models.requests;

import java.util.Objects;

public class GetAvailableCryptoRequest {
    private String token;

    public GetAvailableCryptoRequest() {

    }

    public GetAvailableCryptoRequest(Builder builder) {
        this.token = builder.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAvailableCryptoRequest that = (GetAvailableCryptoRequest) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "GetAvailableCryptoRequest{" +
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


        public GetAvailableCryptoRequest build() {
            return new GetAvailableCryptoRequest(this);
        }
    }
}
