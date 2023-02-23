package com.development.models.requests;

import java.util.Objects;

public class DeleteCryptoInWalletRequest {
    private String token;

    private String userId;
    private String walletName;
    private String cryptoName;
    private String cryptoType;

    private String image;

    private String imageUrl;

    private String cryptoDescription;

    private double cryptoAmount;

    private double cryptoCost;


    public DeleteCryptoInWalletRequest() {

    }


    public DeleteCryptoInWalletRequest(Builder builder) {

        this.token = builder.token;
        this.userId = builder.userId;
        this.walletName = builder.walletName;
        this.cryptoName = builder.cryptoName;
        this.cryptoType = builder.cryptoType;
        this.image = builder.image;
        this.imageUrl = builder.imageUrl;
        this.cryptoDescription = builder.cryptoDescription;
        this.cryptoAmount = builder.cryptoAmount;
        this.cryptoCost = builder.cryptoCost;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getCryptoType() {
        return cryptoType;
    }

    public void setCryptoType(String cryptoType) {
        this.cryptoType = cryptoType;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCryptoDescription() {
        return cryptoDescription;
    }

    public void setCryptoDescription(String cryptoDescription) {
        this.cryptoDescription = cryptoDescription;
    }

    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }

    public double getCryptoCost() {
        return cryptoCost;
    }

    public void setCryptoCost(double cryptoCost) {
        this.cryptoCost = cryptoCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteCryptoInWalletRequest that = (DeleteCryptoInWalletRequest) o;
        return Objects.equals(userId, that.userId) && Objects.equals(walletName, that.walletName) && Objects.equals(cryptoName, that.cryptoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, walletName, cryptoName);
    }

    @Override
    public String toString() {
        return "DeleteCryptoInWalletRequest{" +
                "token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", walletName='" + walletName + '\'' +
                ", cryptoName='" + cryptoName + '\'' +
                ", cryptoType='" + cryptoType + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", cryptoDescription='" + cryptoDescription + '\'' +
                ", cryptoAmount=" + cryptoAmount +
                ", cryptoCost=" + cryptoCost +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String token;
        private String userId;
        private String walletName;
        private String cryptoName;
        private String cryptoType;

        private String image;

        private String imageUrl;

        private String cryptoDescription;

        private double cryptoAmount;

        private double cryptoCost;

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

        public Builder withCryptoName(String useCryptoName) {
            this.cryptoName = useCryptoName;
            return this;
        }

        public Builder withCryptoType(String useCryptoType) {
            this.cryptoType = useCryptoType;
            return this;
        }

        public Builder withToken(String useToken) {
            this.token = useToken;
            return this;
        }

        public Builder withImage(String useImage) {
            this.image = useImage;
            return this;
        }

        public Builder withImageUrl(String useImageUrl) {
            this.imageUrl = useImageUrl;
            return this;
        }

        public Builder withCryptoDescription(String useCryptoDescription) {
            this.cryptoDescription = useCryptoDescription;
            return this;
        }
        public Builder withCryptoAmount(double useCryptoAmount) {
            this.cryptoAmount = useCryptoAmount;
            return this;
        }

        public Builder withCryptoCost(double useCryptoCost) {
            this.cryptoCost = useCryptoCost;
            return this;
        }

        public DeleteCryptoInWalletRequest build() {
            return new DeleteCryptoInWalletRequest(this);
        }
    }
}
