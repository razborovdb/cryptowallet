package com.development.activity;

import com.development.dynamodb.UsersDao;
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
public final class CreateUserActivity_Factory implements Factory<CreateUserActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  public CreateUserActivity_Factory(Provider<UsersDao> usersDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public CreateUserActivity get() {
    return newInstance(usersDaoProvider.get());
  }

  public static CreateUserActivity_Factory create(Provider<UsersDao> usersDaoProvider) {
    return new CreateUserActivity_Factory(usersDaoProvider);
  }

  public static CreateUserActivity newInstance(UsersDao usersDao) {
    return new CreateUserActivity(usersDao);
  }
}
