package com.development.models.requests;

import java.util.Objects;

public class GetCryptoFromAvailableCryptoRequest {
    private String token;

    private String cryptoName;


    public GetCryptoFromAvailableCryptoRequest() {

    }


    public GetCryptoFromAvailableCryptoRequest(Builder builder) {

        this.token = builder.token;
        this.cryptoName = builder.cryptoName;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCryptoFromAvailableCryptoRequest that = (GetCryptoFromAvailableCryptoRequest) o;
        return Objects.equals(cryptoName, that.cryptoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoName);
    }

    @Override
    public String toString() {
        return "GetCryptoFromAvailableCryptoRequest{" +
                "token='" + token + '\'' +
                ", cryptoName='" + cryptoName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;
        private String cryptoName;

        private Builder() {

        }

        public Builder withToken(String useToken) {
            this.token = useToken;
            return this;
        }

        public Builder withCryptoName(String useCryptoName) {
            this.cryptoName = useCryptoName;
            return this;
        }

        public GetCryptoFromAvailableCryptoRequest build() {
            return new GetCryptoFromAvailableCryptoRequest(this);
        }
    }
}
