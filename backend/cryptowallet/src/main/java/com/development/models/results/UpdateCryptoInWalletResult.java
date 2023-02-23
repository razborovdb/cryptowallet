package com.development.models.results;

import com.development.dynamodb.models.CryptoCurrenciesModel;

import java.util.List;
import java.util.Objects;

public class UpdateCryptoInWalletResult {
    private String userId;
    private String walletName;
    private String walletDescription;
    private Double cryptosCount;
    private Double cryptosCost;
    private List<CryptoCurrenciesModel> cryptocurrenciesList;


    public UpdateCryptoInWalletResult() {

    }

    public UpdateCryptoInWalletResult(Builder builder) {
        this.userId = builder.userId;
        this.walletName = builder.walletName;
        this.walletDescription = builder.walletDescription;
        this.cryptosCount = builder.cryptosCount;
        this.cryptosCost = builder.cryptosCost;
        this.cryptocurrenciesList = builder.cryptocurrenciesList;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getWalletDescription() {
        return walletDescription;
    }

    public void setWalletDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }

    public Double getCryptosCount() {
        return cryptosCount;
    }

    public void setCryptosCount(Double cryptosCount) {
        this.cryptosCount = cryptosCount;
    }

    public Double getCryptosCost() {
        return cryptosCost;
    }

    public void setCryptosCost(Double cryptosCost) {
        this.cryptosCost = cryptosCost;
    }

    public List<CryptoCurrenciesModel> getCryptocurrenciesList() {
        return cryptocurrenciesList;
    }

    public void setCryptocurrenciesList(List<CryptoCurrenciesModel> cryptocurrenciesList) {
        this.cryptocurrenciesList = cryptocurrenciesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCryptoInWalletResult that = (UpdateCryptoInWalletResult) o;
        return Objects.equals(userId, that.userId) && Objects.equals(walletName, that.walletName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, walletName);
    }

    @Override
    public String toString() {
        return "UpdateCryptoInWalletResult{" +
                "userId='" + userId + '\'' +
                ", walletName='" + walletName + '\'' +
                ", walletDescription='" + walletDescription + '\'' +
                ", cryptosCount=" + cryptosCount +
                ", cryptosCost=" + cryptosCost +
                ", cryptocurrenciesList=" + cryptocurrenciesList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String userId;
        private String walletName;
        private String walletDescription;
        private Double cryptosCount;
        private Double cryptosCost;
        private List<CryptoCurrenciesModel> cryptocurrenciesList;

        private Builder() {

        }

        public Builder withUserId(String useUserId) {
            this.userId = useUserId;
            return this;
        }

        public Builder withWalletName(String useWalletName) {
            this.walletName = useWalletName;
            return this;
        }

        public Builder withWalletDescription(String useWalletDescription) {
            this.walletDescription = useWalletDescription;
            return this;
        }

        public Builder withCryptosCount(double useCryptosCount) {
            this.cryptosCount = useCryptosCount;
            return this;
        }

        public Builder withCryptosCost(double useCryptosCost) {
            this.cryptosCost = useCryptosCost;
            return this;
        }

        public Builder withCryptocurrenciesList(List<CryptoCurrenciesModel> useCryptocurrenciesList) {
            this.cryptocurrenciesList = useCryptocurrenciesList;
            return this;
        }

        public UpdateCryptoInWalletResult build() {
            return new UpdateCryptoInWalletResult(this);
        }
    }
}
