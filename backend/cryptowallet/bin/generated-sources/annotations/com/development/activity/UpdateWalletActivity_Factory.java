package com.development.activity;

import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
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
public final class UpdateWalletActivity_Factory implements Factory<UpdateWalletActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<WalletDao> walletDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public UpdateWalletActivity_Factory(Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.walletDaoProvider = walletDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public UpdateWalletActivity get() {
    return newInstance(usersDaoProvider.get(), walletDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static UpdateWalletActivity_Factory create(Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    return new UpdateWalletActivity_Factory(usersDaoProvider, walletDaoProvider, cryptoDaoProvider);
  }

  public static UpdateWalletActivity newInstance(UsersDao usersDao, WalletDao walletDao,
      CryptoDao cryptoDao) {
    return new UpdateWalletActivity(usersDao, walletDao, cryptoDao);
  }
}
