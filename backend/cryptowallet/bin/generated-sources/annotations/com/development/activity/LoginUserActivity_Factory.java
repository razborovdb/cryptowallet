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
public final class LoginUserActivity_Factory implements Factory<LoginUserActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  public LoginUserActivity_Factory(Provider<UsersDao> usersDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public LoginUserActivity get() {
    return newInstance(usersDaoProvider.get());
  }

  public static LoginUserActivity_Factory create(Provider<UsersDao> usersDaoProvider) {
    return new LoginUserActivity_Factory(usersDaoProvider);
  }

  public static LoginUserActivity newInstance(UsersDao usersDao) {
    return new LoginUserActivity(usersDao);
  }
}
