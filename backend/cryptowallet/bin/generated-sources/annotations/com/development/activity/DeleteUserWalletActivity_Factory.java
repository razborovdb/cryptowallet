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
public final class DeleteUserWalletActivity_Factory implements Factory<DeleteUserWalletActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<WalletDao> walletDaoProvider;

  public DeleteUserWalletActivity_Factory(
      Provider<UsersDao> usersDaoProvider, Provider<WalletDao> walletDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.walletDaoProvider = walletDaoProvider;
  }

  @Override
  public DeleteUserWalletActivity get() {
    return new DeleteUserWalletActivity(usersDaoProvider.get(), walletDaoProvider.get());
  }

  public static DeleteUserWalletActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<WalletDao> walletDaoProvider) {
    return new DeleteUserWalletActivity_Factory(usersDaoProvider, walletDaoProvider);
  }
}
