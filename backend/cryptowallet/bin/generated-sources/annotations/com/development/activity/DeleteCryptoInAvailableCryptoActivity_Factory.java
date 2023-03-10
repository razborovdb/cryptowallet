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
public final class DeleteCryptoInAvailableCryptoActivity_Factory
    implements Factory<DeleteCryptoInAvailableCryptoActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public DeleteCryptoInAvailableCryptoActivity_Factory(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public DeleteCryptoInAvailableCryptoActivity get() {
    return new DeleteCryptoInAvailableCryptoActivity(
        usersDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static DeleteCryptoInAvailableCryptoActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    return new DeleteCryptoInAvailableCryptoActivity_Factory(usersDaoProvider, cryptoDaoProvider);
  }
}
