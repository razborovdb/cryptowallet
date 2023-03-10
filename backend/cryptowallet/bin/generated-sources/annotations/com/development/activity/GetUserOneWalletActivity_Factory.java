package com.development.activity;

import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetUserOneWalletActivity_Factory implements Factory<GetUserOneWalletActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<WalletDao> walletDaoProvider;

  public GetUserOneWalletActivity_Factory(
      Provider<UsersDao> usersDaoProvider, Provider<WalletDao> walletDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.walletDaoProvider = walletDaoProvider;
  }

  @Override
  public GetUserOneWalletActivity get() {
    return new GetUserOneWalletActivity(usersDaoProvider.get(), walletDaoProvider.get());
  }

  public static GetUserOneWalletActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<WalletDao> walletDaoProvider) {
    return new GetUserOneWalletActivity_Factory(usersDaoProvider, walletDaoProvider);
  }
}
