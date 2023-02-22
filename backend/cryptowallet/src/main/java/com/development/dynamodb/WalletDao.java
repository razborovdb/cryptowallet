package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.development.dynamodb.models.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class WalletDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public WalletDao(DynamoDBMapper dynamoDbMapper) {
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

    public List<Wallet> getAllWalletsForCustomerId(String userId) {
        Map<String, AttributeValue> walletAV = new HashMap<String, AttributeValue>();
        walletAV.put(":val1", new AttributeValue().withS(userId));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("userid = :val1")
                .withExpressionAttributeValues(walletAV)
                .withConsistentRead(false);
        try {
            List<Wallet> walletList = new ArrayList<>(dynamoDbMapper.scan(Wallet.class, scanExpression));

            return walletList;
        } catch (Exception e) {
            return null;
        }


    }

    public Wallet addWallet(Wallet wallet) {
        try {

            dynamoDbMapper.save(wallet);
            return wallet;
        } catch (Exception e) {

            return null;
        }

    }

    public Wallet updateWallet(Wallet wallet) {
        try {
            dynamoDbMapper.save(wallet);
            return wallet;
        } catch (Exception e) {
            return null;
        }

    }

    public Wallet deleteWallet(Wallet wallet) {

        try {
            dynamoDbMapper.delete(wallet);
            return wallet;
        } catch (Exception e) {
            return null;
        }

    }
}
