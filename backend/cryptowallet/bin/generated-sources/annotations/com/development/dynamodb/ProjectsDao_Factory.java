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
public final class ProjectsDao_Factory implements Factory<ProjectsDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public ProjectsDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public ProjectsDao get() {
    return newInstance(dynamoDbMapperProvider.get());
  }

  public static ProjectsDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new ProjectsDao_Factory(dynamoDbMapperProvider);
  }

  public static ProjectsDao newInstance(DynamoDBMapper dynamoDbMapper) {
    return new ProjectsDao(dynamoDbMapper);
  }
}
