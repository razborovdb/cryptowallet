package com.development.activity;

import com.development.dynamodb.UsersDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class LoginUserActivity_Factory implements Factory<LoginUserActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  public LoginUserActivity_Factory(Provider<UsersDao> usersDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public LoginUserActivity get() {
    return new LoginUserActivity(usersDaoProvider.get());
  }

  public static LoginUserActivity_Factory create(Provider<UsersDao> usersDaoProvider) {
    return new LoginUserActivity_Factory(usersDaoProvider);
  }
}
