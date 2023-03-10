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
public final class AddCryptoToAvailableCryptoActivity_Factory
    implements Factory<AddCryptoToAvailableCryptoActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public AddCryptoToAvailableCryptoActivity_Factory(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public AddCryptoToAvailableCryptoActivity get() {
    return new AddCryptoToAvailableCryptoActivity(usersDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static AddCryptoToAvailableCryptoActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    return new AddCryptoToAvailableCryptoActivity_Factory(usersDaoProvider, cryptoDaoProvider);
  }
}
