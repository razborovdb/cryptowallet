package com.development.activity;

import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetUserWalletActivity_Factory implements Factory<GetUserWalletActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<WalletDao> walletDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public GetUserWalletActivity_Factory(
      Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider,
      Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.walletDaoProvider = walletDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public GetUserWalletActivity get() {
    return new GetUserWalletActivity(
        usersDaoProvider.get(), walletDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static GetUserWalletActivity_Factory create(
      Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider,
      Provider<CryptoDao> cryptoDaoProvider) {
    return new GetUserWalletActivity_Factory(
        usersDaoProvider, walletDaoProvider, cryptoDaoProvider);
  }
}
