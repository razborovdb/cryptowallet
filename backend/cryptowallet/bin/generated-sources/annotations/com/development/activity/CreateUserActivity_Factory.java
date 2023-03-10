package com.development.activity;

import com.development.dynamodb.UsersDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CreateUserActivity_Factory implements Factory<CreateUserActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  public CreateUserActivity_Factory(Provider<UsersDao> usersDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public CreateUserActivity get() {
    return new CreateUserActivity(usersDaoProvider.get());
  }

  public static CreateUserActivity_Factory create(Provider<UsersDao> usersDaoProvider) {
    return new CreateUserActivity_Factory(usersDaoProvider);
  }
}
