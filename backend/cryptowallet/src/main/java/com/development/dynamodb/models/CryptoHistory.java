package com.development.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "cryptohistory")
public class CryptoHistory {
    private String userId;
    private String recordDate;
    private List<Wallet> walletsList;

    @DynamoDBHashKey(attributeName = "userid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "recorddate")
    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    @DynamoDBAttribute(attributeName = "walletsList")
    public List<Wallet> getWalletsList() {
        return walletsList;
    }

    public void setWalletsList(List<Wallet> walletsList) {
        this.walletsList = walletsList;
    }


}
