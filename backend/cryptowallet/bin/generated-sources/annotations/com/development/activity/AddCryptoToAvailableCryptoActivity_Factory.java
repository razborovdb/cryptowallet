package com.development.activity;

import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.UsersDao;
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
public final class AddCryptoToAvailableCryptoActivity_Factory implements Factory<AddCryptoToAvailableCryptoActivity> {
  private final Provider<UsersDao> usersDaoProvider;

  private final Provider<CryptoDao> cryptoDaoProvider;

  public AddCryptoToAvailableCryptoActivity_Factory(Provider<UsersDao> usersDaoProvider,
      Provider<CryptoDao> cryptoDaoProvider) {
    this.usersDaoProvider = usersDaoProvider;
    this.cryptoDaoProvider = cryptoDaoProvider;
  }

  @Override
  public AddCryptoToAvailableCryptoActivity get() {
    return newInstance(usersDaoProvider.get(), cryptoDaoProvider.get());
  }

  public static AddCryptoToAvailableCryptoActivity_Factory create(
      Provider<UsersDao> usersDaoProvider, Provider<CryptoDao> cryptoDaoProvider) {
    return new AddCryptoToAvailableCryptoActivity_Factory(usersDaoProvider, cryptoDaoProvider);
  }

  public static AddCryptoToAvailableCryptoActivity newInstance(UsersDao usersDao,
      CryptoDao cryptoDao) {
    return new AddCryptoToAvailableCryptoActivity(usersDao, cryptoDao);
  }
}
