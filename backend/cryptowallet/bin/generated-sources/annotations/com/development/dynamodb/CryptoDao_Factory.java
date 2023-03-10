package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CryptoDao_Factory implements Factory<CryptoDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public CryptoDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public CryptoDao get() {
    return new CryptoDao(dynamoDbMapperProvider.get());
  }

  public static CryptoDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new CryptoDao_Factory(dynamoDbMapperProvider);
  }
}
