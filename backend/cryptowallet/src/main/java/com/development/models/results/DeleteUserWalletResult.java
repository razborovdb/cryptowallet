package com.development.models.results;

import com.development.dynamodb.models.CryptoCurrenciesModel;

import java.util.List;
import java.util.Objects;

public class DeleteUserWalletResult {
    private String walletName;

    public DeleteUserWalletResult() {

    }

    public DeleteUserWalletResult(Builder builder) {
        this.walletName = builder.walletName;
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
        DeleteUserWalletResult that = (DeleteUserWalletResult) o;
        return Objects.equals(walletName, that.walletName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletName);
    }

    @Override
    public String toString() {
        return "DeleteUserWalletResult{" +
                "walletName='" + walletName + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String walletName;

        private Builder() {

        }

        public Builder withWalletName(String useWalletName) {
            this.walletName = useWalletName;
            return this;
        }

        public DeleteUserWalletResult build() {
            return new DeleteUserWalletResult(this);
        }
    }
}
