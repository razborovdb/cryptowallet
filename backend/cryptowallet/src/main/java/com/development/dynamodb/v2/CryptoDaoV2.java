package com.development.dynamodb.v2;

import com.development.dynamodb.models.CryptoCurrencies;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class CryptoDaoV2 {
    DynamoDbEnhancedClient dynamoDbEnhancedClient = getDynamoDbEnhancedClient();

    public CryptoCurrencies updateCrypto(CryptoCurrencies crypto) {

        try {
//            dynamoDbClient.save(crypto);
            return crypto;
        } catch (Exception e) {
            return null;
        }
    }



    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {

        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_WEST_2;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(ddb)
                .build();

        return enhancedClient;
    }
}
