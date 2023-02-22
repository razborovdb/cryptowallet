package com.development.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "cryptowallets")
public class Wallet {
    private String userId;
    private String walletName;
    private String walletDescription;
    private Double cryptosCount;
    private Double cryptosCost;
    private List<CryptoCurrenciesModel> cryptocurrenciesList;

    @DynamoDBHashKey(attributeName = "userid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "walletname")
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @DynamoDBAttribute(attributeName = "walletdescription")
    public String getWalletDescription() {
        return walletDescription;
    }

    public void setWalletDescription(String walletDescription) {
        this.walletDescription = walletDescription;
    }

    @DynamoDBAttribute(attributeName = "cryptoscount")
    public Double getCryptosCount() {
        return cryptosCount;
    }

    public void setCryptosCount(Double cryptosCount) {
        this.cryptosCount = cryptosCount;
    }

    @DynamoDBAttribute(attributeName = "cryptoscost")
    public Double getCryptosCost() {
        return cryptosCost;
    }

    public void setCryptosCost(Double cryptosCost) {
        this.cryptosCost = cryptosCost;
    }

    @DynamoDBAttribute(attributeName = "cryptocurrencieslist")
    public List<CryptoCurrenciesModel> getCryptocurrenciesList() {
        return cryptocurrenciesList;
    }

    public void setCryptocurrenciesList(List<CryptoCurrenciesModel> cryptocurrenciesList) {
        this.cryptocurrenciesList = cryptocurrenciesList;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "userId='" + userId + '\'' +
                ", walletName='" + walletName + '\'' +
                ", walletDescription='" + walletDescription + '\'' +
                ", cryptosCount=" + cryptosCount +
                ", cryptosCost=" + cryptosCost +
                ", cryptocurrenciesList=" + cryptocurrenciesList +
                '}';
    }
}
