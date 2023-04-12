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
public final class CryptoDao_Factory implements Factory<CryptoDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public CryptoDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public CryptoDao get() {
    return newInstance(dynamoDbMapperProvider.get());
  }

  public static CryptoDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new CryptoDao_Factory(dynamoDbMapperProvider);
  }

  public static CryptoDao newInstance(DynamoDBMapper dynamoDbMapper) {
    return new CryptoDao(dynamoDbMapper);
  }
}
