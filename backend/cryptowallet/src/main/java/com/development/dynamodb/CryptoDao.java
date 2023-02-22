package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.development.dynamodb.models.CryptoCurrencies;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CryptoDao {
    private final DynamoDBMapper dynamoDbMapper;

    @Inject
    public CryptoDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }


    public CryptoCurrencies createCrypto(CryptoCurrencies crypto) {

        try {
            dynamoDbMapper.save(crypto);
            return crypto;
        } catch (Exception e) {
            return null;
        }
    }

    public CryptoCurrencies getCrypto(String cryptoName) {
        CryptoCurrencies crypto = new CryptoCurrencies();
        crypto.setCryptoName(cryptoName);

        DynamoDBQueryExpression<CryptoCurrencies> queryExpression = new DynamoDBQueryExpression<CryptoCurrencies>()
                .withHashKeyValues(crypto);

        try {
            PaginatedQueryList<CryptoCurrencies> cryptosList = dynamoDbMapper.query(CryptoCurrencies.class, queryExpression);
            if (cryptosList.size() > 0) {
                return cryptosList.get(0);
            }
        } catch (Exception e) {

        }

        return null;
    }

    public CryptoCurrencies deleteCrypto(CryptoCurrencies crypto) {

        try {
            dynamoDbMapper.delete(crypto);
            return crypto;
        } catch (Exception e) {
            return null;
        }
    }

    public CryptoCurrencies updateCrypto(CryptoCurrencies crypto) {

        try {
            dynamoDbMapper.save(crypto);
            return crypto;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CryptoCurrencies> getAllCryptocurrency() {
        Map<String, AttributeValue> cryptocurrencyAV = new HashMap<String, AttributeValue>();
        cryptocurrencyAV.put(":val1", new AttributeValue().withN(Double.toString(0)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("cryptocost > :val1")
                .withExpressionAttributeValues(cryptocurrencyAV)
                .withConsistentRead(false);

        List<CryptoCurrencies> cryptocurrencyList = new ArrayList<>(dynamoDbMapper.scan(CryptoCurrencies.class,
                scanExpression));
        if (cryptocurrencyList == null) {
            cryptocurrencyList = new ArrayList<>();
        }

        return cryptocurrencyList;
    }
}
