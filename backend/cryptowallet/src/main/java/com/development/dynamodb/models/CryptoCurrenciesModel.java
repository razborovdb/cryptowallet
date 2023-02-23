package com.development.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

@DynamoDBTable(tableName = "cryptomodel")
public class CryptoCurrenciesModel {
    private String cryptoName;
    private String cryptoType;

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

    @DynamoDBAttribute(attributeName = "cryptotype")
    public String getCryptoType() {
        return cryptoType;
    }

    public void setCryptoType(String cryptoType) {
        this.cryptoType = cryptoType;
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

    public CryptoCurrenciesModel() {

    }

    public CryptoCurrenciesModel(String cryptoName, String cryptoType, String image, String imageUrl, String cryptoDescription, double cryptoAmount, double cryptoCost) {
        this.cryptoName = cryptoName;
        this.cryptoType = cryptoType;
        this.image = image;
        this.imageUrl = imageUrl;
        this.cryptoDescription = cryptoDescription;
        this.cryptoAmount = cryptoAmount;
        this.cryptoCost = cryptoCost;
    }

    @Override
    public String toString() {
        return "CryptoCurrenciesModel{" +
                "cryptoName='" + cryptoName + '\'' +
                ", cryptoType='" + cryptoType + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", cryptoDescription='" + cryptoDescription + '\'' +
                ", cryptoAmount=" + cryptoAmount +
                ", cryptoCost=" + cryptoCost +
                '}';
    }
}
