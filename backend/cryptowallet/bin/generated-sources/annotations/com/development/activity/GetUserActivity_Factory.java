package com.development.activity;

import com.development.dynamodb.UsersDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetUserActivity_Factory implements Factory<GetUserActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  public GetUserActivity_Factory(Provider<UsersDao> usersDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public GetUserActivity get() {
    return new GetUserActivity(usersDaoProvider.get());
  }

  public static GetUserActivity_Factory create(Provider<UsersDao> usersDaoProvider) {
    return new GetUserActivity_Factory(usersDaoProvider);
  }
}
