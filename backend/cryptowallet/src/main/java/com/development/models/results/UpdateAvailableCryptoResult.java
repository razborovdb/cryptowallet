package com.development.models.results;

import com.development.dynamodb.models.CryptoCurrencies;

import java.util.List;

public class UpdateAvailableCryptoResult {
    private List<CryptoCurrencies> cryptocurrencyList;

    public UpdateAvailableCryptoResult(Builder builder) {
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
        return "UpdateAvailableCryptoResult{" +
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
         * UpdateAvailableCryptocurrenciesResult.
         * @return - UpdateAvailableCryptocurrenciesResult
         */
        public UpdateAvailableCryptoResult build() {
            return new UpdateAvailableCryptoResult(this);
        }
    }
}
