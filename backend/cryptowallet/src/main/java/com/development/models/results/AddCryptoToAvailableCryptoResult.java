package com.development.models.results;

import java.util.Objects;

public class AddCryptoToAvailableCryptoResult {

    private String cryptoName;

    private String image;

    private String imageUrl;

    private String cryptoDescription;

    private double cryptoAmount;

    private double cryptoCost;


    public AddCryptoToAvailableCryptoResult() {

    }


    public AddCryptoToAvailableCryptoResult(Builder builder) {
        this.cryptoName = builder.cryptoName;
        this.image = builder.image;
        this.imageUrl = builder.imageUrl;
        this.cryptoDescription = builder.cryptoDescription;
        this.cryptoAmount = builder.cryptoAmount;
        this.cryptoCost = builder.cryptoCost;
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
        AddCryptoToAvailableCryptoResult that = (AddCryptoToAvailableCryptoResult) o;
        return Objects.equals(cryptoName, that.cryptoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptoName);
    }

    @Override
    public String toString() {
        return "AddCryptoToAvailableCryptoResult{" +
                "cryptoName='" + cryptoName + '\'' +
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
        private String cryptoName;

        private String image;

        private String imageUrl;

        private String cryptoDescription;

        private double cryptoAmount;

        private double cryptoCost;

        private Builder() {

        }

        public Builder withCryptoName(String useCryptoName) {
            this.cryptoName = useCryptoName;
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

        public AddCryptoToAvailableCryptoResult build() {
            return new AddCryptoToAvailableCryptoResult(this);
        }
    }
}
