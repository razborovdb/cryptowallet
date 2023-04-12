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
public final class UsersDao_Factory implements Factory<UsersDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public UsersDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public UsersDao get() {
    return newInstance(dynamoDbMapperProvider.get());
  }

  public static UsersDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new UsersDao_Factory(dynamoDbMapperProvider);
  }

  public static UsersDao newInstance(DynamoDBMapper dynamoDbMapper) {
    return new UsersDao(dynamoDbMapper);
  }
}
