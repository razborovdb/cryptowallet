package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ProjectsDao_Factory implements Factory<ProjectsDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public ProjectsDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public ProjectsDao get() {
    return new ProjectsDao(dynamoDbMapperProvider.get());
  }

  public static ProjectsDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new ProjectsDao_Factory(dynamoDbMapperProvider);
  }
}
