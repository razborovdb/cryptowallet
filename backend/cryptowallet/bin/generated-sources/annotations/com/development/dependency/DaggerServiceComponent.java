package com.development.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.development.activity.AddCryptoToAvailableCryptoActivity;
import com.development.activity.AddCryptoToWalletActivity;
import com.development.activity.AddWalletActivity;
import com.development.activity.CreateUserActivity;
import com.development.activity.DeleteCryptoInAvailableCryptoActivity;
import com.development.activity.DeleteCryptoInWalletActivity;
import com.development.activity.DeleteUserWalletActivity;
import com.development.activity.GetAvailableCryptoActivity;
import com.development.activity.GetCryptoFromAvailableCryptoActivity;
import com.development.activity.GetProjectsActivity;
import com.development.activity.GetUserActivity;
import com.development.activity.GetUserOneWalletActivity;
import com.development.activity.GetUserWalletActivity;
import com.development.activity.LoginUserActivity;
import com.development.activity.UpdateCryptoInAvailableCryptoActivity;
import com.development.activity.UpdateCryptoInWalletActivity;
import com.development.activity.UpdateWalletActivity;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.ProjectsDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

  private DaggerServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  private ProjectsDao getProjectsDao() {
    return new ProjectsDao(provideDynamoDBMapperProvider.get());
  }

  private UsersDao getUsersDao() {
    return new UsersDao(provideDynamoDBMapperProvider.get());
  }

  private CryptoDao getCryptoDao() {
    return new CryptoDao(provideDynamoDBMapperProvider.get());
  }

  private WalletDao getWalletDao() {
    return new WalletDao(provideDynamoDBMapperProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideDynamoDBMapperProvider =
        DoubleCheck.provider(DaoModule_ProvideDynamoDBMapperFactory.create(builder.daoModule));
  }

  @Override
  public GetProjectsActivity provideGetProjectsActivity() {
    return new GetProjectsActivity(getProjectsDao());
  }

  @Override
  public CreateUserActivity provideCreateUserActivity() {
    return new CreateUserActivity(getUsersDao());
  }

  @Override
  public LoginUserActivity provideLoginUserActivity() {
    return new LoginUserActivity(getUsersDao());
  }

  @Override
  public GetUserActivity provideGetUserActivity() {
    return new GetUserActivity(getUsersDao());
  }

  @Override
  public GetAvailableCryptoActivity provideGetAvailableCryptoActivity() {
    return new GetAvailableCryptoActivity(getCryptoDao(), getUsersDao());
  }

  @Override
  public AddCryptoToAvailableCryptoActivity provideAddCryptoToAvailableCryptoActivity() {
    return new AddCryptoToAvailableCryptoActivity(getUsersDao(), getCryptoDao());
  }

  @Override
  public GetCryptoFromAvailableCryptoActivity provideGetCryptoFromAvailableCryptoActivity() {
    return new GetCryptoFromAvailableCryptoActivity(getUsersDao(), getCryptoDao());
  }

  @Override
  public UpdateCryptoInAvailableCryptoActivity provideUpdateCryptoInAvailableCryptoActivity() {
    return new UpdateCryptoInAvailableCryptoActivity(getUsersDao(), getCryptoDao());
  }

  @Override
  public DeleteCryptoInAvailableCryptoActivity provideDeleteCryptoInAvailableCryptoActivity() {
    return new DeleteCryptoInAvailableCryptoActivity(getUsersDao(), getCryptoDao());
  }

  @Override
  public AddWalletActivity provideAddWalletActivity() {
    return new AddWalletActivity(getUsersDao(), getWalletDao(), getCryptoDao());
  }

  @Override
  public GetUserWalletActivity provideGetUserWalletActivity() {
    return new GetUserWalletActivity(getUsersDao(), getWalletDao(), getCryptoDao());
  }

  @Override
  public GetUserOneWalletActivity provideGetUserOneWalletActivity() {
    return new GetUserOneWalletActivity(getUsersDao(), getWalletDao());
  }

  @Override
  public DeleteUserWalletActivity provideDeleteUserWalletActivity() {
    return new DeleteUserWalletActivity(getUsersDao(), getWalletDao());
  }

  @Override
  public UpdateWalletActivity provideUpdateWalletActivity() {
    return new UpdateWalletActivity(getUsersDao(), getWalletDao(), getCryptoDao());
  }

  @Override
  public AddCryptoToWalletActivity provideAddCryptoToWalletActivity() {
    return new AddCryptoToWalletActivity(getUsersDao(), getWalletDao(), getCryptoDao());
  }

  @Override
  public UpdateCryptoInWalletActivity provideUpdateCryptoInWalletActivity() {
    return new UpdateCryptoInWalletActivity(getUsersDao(), getWalletDao(), getCryptoDao());
  }

  @Override
  public DeleteCryptoInWalletActivity provideDeleteCryptoInWalletActivity() {
    return new DeleteCryptoInWalletActivity(getUsersDao(), getWalletDao(), getCryptoDao());
  }

  public static final class Builder {
    private DaoModule daoModule;

    private Builder() {}

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      return new DaggerServiceComponent(this);
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }
  }
}
