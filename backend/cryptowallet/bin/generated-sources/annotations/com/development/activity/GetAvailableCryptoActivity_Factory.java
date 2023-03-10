package com.development.activity;

import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetAvailableCryptoActivity_Factory
    implements Factory<GetAvailableCryptoActivity> {
  private final Provider<CryptoDao> cryptoDaoProvider;

  private final Provider<UsersDao> usersDaoProvider;

  public GetAvailableCryptoActivity_Factory(
      Provider<CryptoDao> cryptoDaoProvider, Provider<UsersDao> usersDaoProvider) {
    this.cryptoDaoProvider = cryptoDaoProvider;
    this.usersDaoProvider = usersDaoProvider;
  }

  @Override
  public GetAvailableCryptoActivity get() {
    return new GetAvailableCryptoActivity(cryptoDaoProvider.get(), usersDaoProvider.get());
  }

  public static GetAvailableCryptoActivity_Factory create(
      Provider<CryptoDao> cryptoDaoProvider, Provider<UsersDao> usersDaoProvider) {
    return new GetAvailableCryptoActivity_Factory(cryptoDaoProvider, usersDaoProvider);
  }
}
