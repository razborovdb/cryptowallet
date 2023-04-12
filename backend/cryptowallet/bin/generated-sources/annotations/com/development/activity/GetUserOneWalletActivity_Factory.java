package com.development.activity;

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
public final class GetUserOneWalletActivity_Factory implements Factory<GetUserOneWalletActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<WalletDao> walletDaoProvider;

  public GetUserOneWalletActivity_Factory(Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.walletDaoProvider = walletDaoProvider;
  }

  @Override
  public GetUserOneWalletActivity get() {
    return newInstance(usersDaoProvider.get(), walletDaoProvider.get());
  }

  public static GetUserOneWalletActivity_Factory create(Provider<UsersDao> usersDaoProvider,
      Provider<WalletDao> walletDaoProvider) {
    return new GetUserOneWalletActivity_Factory(usersDaoProvider, walletDaoProvider);
  }

  public static GetUserOneWalletActivity newInstance(UsersDao usersDao, WalletDao walletDao) {
    return new GetUserOneWalletActivity(usersDao, walletDao);
  }
}
