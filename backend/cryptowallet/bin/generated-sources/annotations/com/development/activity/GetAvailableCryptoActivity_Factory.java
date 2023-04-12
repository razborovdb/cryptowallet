package com.development.activity;

import com.development.dynamodb.CryptoDao;
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
public final class GetAvailableCryptoActivity_Factory implements Factory<GetAvailableCryptoActivity> {
  private final Provider<CryptoDao> cryptoDaoProvider;

  private final Provider<UsersDao> usersDaoProvider;

  public GetAvailableCryptoActivity_Factory(Provider<CryptoDao> cryptoDaoProvider,
      Provider<UsersDao> usersDaoProvider) {
    this.cryptoDaoProvider = cryptoDaoProvider;
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public GetAvailableCryptoActivity get() {
    return newInstance(cryptoDaoProvider.get(), usersDaoProvider.get());
  }

  public static GetAvailableCryptoActivity_Factory create(Provider<CryptoDao> cryptoDaoProvider,
      Provider<UsersDao> usersDaoProvider) {
    return new GetAvailableCryptoActivity_Factory(cryptoDaoProvider, usersDaoProvider);
  }

  public static GetAvailableCryptoActivity newInstance(CryptoDao cryptoDao, UsersDao usersDao) {
    return new GetAvailableCryptoActivity(cryptoDao, usersDao);
  }
}
