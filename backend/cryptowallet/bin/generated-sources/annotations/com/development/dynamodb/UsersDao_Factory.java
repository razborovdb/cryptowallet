package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UsersDao_Factory implements Factory<UsersDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public UsersDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public UsersDao get() {
    return new UsersDao(dynamoDbMapperProvider.get());
  }

  public static UsersDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new UsersDao_Factory(dynamoDbMapperProvider);
  }
}
