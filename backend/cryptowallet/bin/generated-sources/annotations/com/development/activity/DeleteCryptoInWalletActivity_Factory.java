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
public final class DeleteCryptoInWalletActivity_Factory
    implements Factory<DeleteCryptoInWalletActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<WalletDao> walletDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public DeleteCryptoInWalletActivity_Factory(
      Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider,
      Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.walletDaoProvider = walletDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public DeleteCryptoInWalletActivity get() {
    return new DeleteCryptoInWalletActivity(
        usersDaoProvider.get(), walletDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static DeleteCryptoInWalletActivity_Factory create(
      Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider,
      Provider<CryptoDao> cryptoDaoProvider) {
    return new DeleteCryptoInWalletActivity_Factory(
        usersDaoProvider, walletDaoProvider, cryptoDaoProvider);
  }
}
