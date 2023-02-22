package com.development.models.results;

import java.util.Objects;

public class DeleteCryptoInAvailableCryptoResult {

    private String cryptoName;

    public DeleteCryptoInAvailableCryptoResult() {

    }


    public DeleteCryptoInAvailableCryptoResult(Builder builder) {
        this.cryptoName = builder.cryptoName;
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
        DeleteCryptoInAvailableCryptoResult that = (DeleteCryptoInAvailableCryptoResult) o;
        return Objects.equals(cryptoName, that.cryptoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoName);
    }

    @Override
    public String toString() {
        return "DeleteCryptoInAvailableCryptoResult{" +
                "cryptoName='" + cryptoName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cryptoName;

        private Builder() {

        }

        public Builder withCryptoName(String useCryptoName) {
            this.cryptoName = useCryptoName;
            return this;
        }

        public DeleteCryptoInAvailableCryptoResult build() {
            return new DeleteCryptoInAvailableCryptoResult(this);
        }
    }
}
