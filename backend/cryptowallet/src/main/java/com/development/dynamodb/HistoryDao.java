package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.development.dynamodb.models.CryptoHistory;
import com.development.dynamodb.models.Wallet;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public HistoryDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    public Wallet getWallet(String userId, String walletName) {

        try {
            Wallet wallet = dynamoDbMapper.load(Wallet.class, userId, walletName);

            return wallet;
        } catch (Exception e) {

            return null;
        }
    }

    public List<CryptoHistory> getHistoryForUserId(String userId) {
        Map<String, AttributeValue> historyAV = new HashMap<String, AttributeValue>();
        historyAV.put(":val1", new AttributeValue().withS(userId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("userid = :val1")
                .withExpressionAttributeValues(historyAV)
                .withConsistentRead(false);
        try {
            List<CryptoHistory> cryptoHistoryList = new ArrayList<>(dynamoDbMapper.scan(CryptoHistory.class, scanExpression));

            return cryptoHistoryList;
        } catch (Exception e) {
            return null;
        }
    }

    public CryptoHistory addHistoryRecord(CryptoHistory cryptoHistory) {
        try {
            dynamoDbMapper.save(cryptoHistory);
            return cryptoHistory;
        } catch (Exception e) {

            return null;
        }

    }

}
