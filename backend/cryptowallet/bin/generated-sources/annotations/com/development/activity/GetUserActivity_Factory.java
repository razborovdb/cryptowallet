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
public final class GetUserActivity_Factory implements Factory<GetUserActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  public GetUserActivity_Factory(Provider<UsersDao> usersDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public GetUserActivity get() {
    return newInstance(usersDaoProvider.get());
  }

  public static GetUserActivity_Factory create(Provider<UsersDao> usersDaoProvider) {
    return new GetUserActivity_Factory(usersDaoProvider);
  }

  public static GetUserActivity newInstance(UsersDao usersDao) {
    return new GetUserActivity(usersDao);
  }
}
