package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class WalletDao_Factory implements Factory<WalletDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public WalletDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public WalletDao get() {
    return newInstance(dynamoDbMapperProvider.get());
  }

  public static WalletDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new WalletDao_Factory(dynamoDbMapperProvider);
  }

  public static WalletDao newInstance(DynamoDBMapper dynamoDbMapper) {
    return new WalletDao(dynamoDbMapper);
  }
}
