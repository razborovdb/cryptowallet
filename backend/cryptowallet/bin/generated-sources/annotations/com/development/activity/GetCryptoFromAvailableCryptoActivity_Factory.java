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
public final class GetCryptoFromAvailableCryptoActivity_Factory
    implements Factory<GetCryptoFromAvailableCryptoActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public GetCryptoFromAvailableCryptoActivity_Factory(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public GetCryptoFromAvailableCryptoActivity get() {
    return new GetCryptoFromAvailableCryptoActivity(
        usersDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static GetCryptoFromAvailableCryptoActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    return new GetCryptoFromAvailableCryptoActivity_Factory(usersDaoProvider, cryptoDaoProvider);
  }
}
