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
public final class UpdateCryptoInAvailableCryptoActivity_Factory
    implements Factory<UpdateCryptoInAvailableCryptoActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public UpdateCryptoInAvailableCryptoActivity_Factory(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public UpdateCryptoInAvailableCryptoActivity get() {
    return new UpdateCryptoInAvailableCryptoActivity(
        usersDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static UpdateCryptoInAvailableCryptoActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    return new UpdateCryptoInAvailableCryptoActivity_Factory(usersDaoProvider, cryptoDaoProvider);
  }
}
