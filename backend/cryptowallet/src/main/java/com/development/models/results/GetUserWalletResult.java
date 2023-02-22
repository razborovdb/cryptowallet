package com.development.models.results;

import com.development.dynamodb.models.CryptoCurrenciesModel;
import com.development.dynamodb.models.Wallet;

import java.util.List;
import java.util.Objects;

public class GetUserWalletResult {
    private List<Wallet> walletList;


    public GetUserWalletResult() {

    }

    public GetUserWalletResult(Builder builder) {
        this.walletList = builder.walletList;


    }

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }

    @Override
    public String toString() {
        return "GetUserWalletResult{" +
                "walletList=" + walletList +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Wallet> walletList;

        private Builder() {

        }

        public Builder withWalletList(List<Wallet> useWalletList) {
            this.walletList = useWalletList;
            return this;
        }

        public GetUserWalletResult build() {
            return new GetUserWalletResult(this);
        }
    }
}
