package com.development.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "cryptosv2")
public class CryptoCurrenciesV2 {
    private String cryptoName;

    private String image;

    private String imageUrl;

    private String cryptoDescription;

    private double cryptoAmount;

    private double cryptoCost;

    @DynamoDBHashKey(attributeName = "cryptoname")
    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }
    @DynamoDBAttribute(attributeName = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @DynamoDBAttribute(attributeName = "imageurl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @DynamoDBAttribute(attributeName = "cryptodescription")
    public String getCryptoDescription() {
        return cryptoDescription;
    }

    public void setCryptoDescription(String cryptoDescription) {
        this.cryptoDescription = cryptoDescription;
    }

    @DynamoDBAttribute(attributeName = "cryptoamount")
    public double getCryptoAmount() {
        return cryptoAmount;
    }

    public void setCryptoAmount(double cryptoAmount) {
        this.cryptoAmount = cryptoAmount;
    }
    @DynamoDBAttribute(attributeName = "cryptocost")
    public double getCryptoCost() {
        return cryptoCost;
    }

    public void setCryptoCost(double cryptoCost) {
        this.cryptoCost = cryptoCost;
    }

    @Override
    public String toString() {
        return "CryptoCurrencies{" +
                "cryptoName='" + cryptoName + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", cryptoDescription='" + cryptoDescription + '\'' +
                ", cryptoAmount=" + cryptoAmount +
                ", cryptoCost=" + cryptoCost +
                '}';
    }
}
