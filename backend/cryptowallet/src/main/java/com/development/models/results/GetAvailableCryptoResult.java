package com.development.models.results;

import com.development.dynamodb.models.CryptoCurrencies;

import java.util.List;

public class GetAvailableCryptoResult {
    private List<CryptoCurrencies> cryptocurrencyList;

    public GetAvailableCryptoResult(Builder builder) {
        this.cryptocurrencyList = builder.cryptocurrencyList;
    }

    public List<CryptoCurrencies> getCryptocurrencyList() {
        return cryptocurrencyList;
    }

    public void setCryptocurrencyList(List<CryptoCurrencies> cryptocurrencyList) {
        this.cryptocurrencyList = cryptocurrencyList;
    }

    @Override
    public String toString() {
        return "GetAvailableCryptoResult{" +
                "cryptocurrencyList=" + cryptocurrencyList +
                '}';
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<CryptoCurrencies> cryptocurrencyList;
        private Builder() {

        }

        public Builder withCryptocurrencyList(List<CryptoCurrencies> cryptocurrencyListToUse) {
            this.cryptocurrencyList = cryptocurrencyListToUse;
            return this;
        }

        /**
         * GetAvailableCryptocurrenciesResult.
         * @return -GetAvailableCryptocurrenciesResult
         */
        public GetAvailableCryptoResult build() {
            return new GetAvailableCryptoResult(this);
        }
    }
}
