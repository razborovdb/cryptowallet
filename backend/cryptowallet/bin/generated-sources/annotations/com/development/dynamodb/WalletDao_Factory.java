package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WalletDao_Factory implements Factory<WalletDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public WalletDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public WalletDao get() {
    return new WalletDao(dynamoDbMapperProvider.get());
  }

  public static WalletDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new WalletDao_Factory(dynamoDbMapperProvider);
  }
}
