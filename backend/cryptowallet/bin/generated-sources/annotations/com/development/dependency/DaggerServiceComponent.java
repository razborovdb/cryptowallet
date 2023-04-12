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
import com.development.activity.UpdateAvailableCryptoActivity;
import com.development.activity.UpdateCryptoInAvailableCryptoActivity;
import com.development.activity.UpdateCryptoInWalletActivity;
import com.development.activity.UpdateWalletActivity;
import com.development.dynamodb.CryptoDao;
import com.development.dynamodb.ProjectsDao;
import com.development.dynamodb.UsersDao;
import com.development.dynamodb.WalletDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DaggerServiceComponent {
  private DaggerServiceComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private DaoModule daoModule;

    private Builder() {
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      return new ServiceComponentImpl(daoModule);
    }
  }

  private static final class ServiceComponentImpl implements ServiceComponent {
    private final ServiceComponentImpl serviceComponentImpl = this;

    private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

    private ServiceComponentImpl(DaoModule daoModuleParam) {

      initialize(daoModuleParam);

    }

    private ProjectsDao projectsDao() {
      return new ProjectsDao(provideDynamoDBMapperProvider.get());
    }

    private UsersDao usersDao() {
      return new UsersDao(provideDynamoDBMapperProvider.get());
    }

    private CryptoDao cryptoDao() {
      return new CryptoDao(provideDynamoDBMapperProvider.get());
    }

    private WalletDao walletDao() {
      return new WalletDao(provideDynamoDBMapperProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final DaoModule daoModuleParam) {
      this.provideDynamoDBMapperProvider = DoubleCheck.provider(DaoModule_ProvideDynamoDBMapperFactory.create(daoModuleParam));
    }

    @Override
    public GetProjectsActivity provideGetProjectsActivity() {
      return new GetProjectsActivity(projectsDao());
    }

    @Override
    public CreateUserActivity provideCreateUserActivity() {
      return new CreateUserActivity(usersDao());
    }

    @Override
    public LoginUserActivity provideLoginUserActivity() {
      return new LoginUserActivity(usersDao());
    }

    @Override
    public GetUserActivity provideGetUserActivity() {
      return new GetUserActivity(usersDao());
    }

    @Override
    public GetAvailableCryptoActivity provideGetAvailableCryptoActivity() {
      return new GetAvailableCryptoActivity(cryptoDao(), usersDao());
    }

    @Override
    public UpdateAvailableCryptoActivity provideUpdateAvailableCryptoActivity() {
      return new UpdateAvailableCryptoActivity(cryptoDao(), usersDao());
    }

    @Override
    public AddCryptoToAvailableCryptoActivity provideAddCryptoToAvailableCryptoActivity() {
      return new AddCryptoToAvailableCryptoActivity(usersDao(), cryptoDao());
    }

    @Override
    public GetCryptoFromAvailableCryptoActivity provideGetCryptoFromAvailableCryptoActivity() {
      return new GetCryptoFromAvailableCryptoActivity(usersDao(), cryptoDao());
    }

    @Override
    public UpdateCryptoInAvailableCryptoActivity provideUpdateCryptoInAvailableCryptoActivity() {
      return new UpdateCryptoInAvailableCryptoActivity(usersDao(), cryptoDao());
    }

    @Override
    public DeleteCryptoInAvailableCryptoActivity provideDeleteCryptoInAvailableCryptoActivity() {
      return new DeleteCryptoInAvailableCryptoActivity(usersDao(), cryptoDao());
    }

    @Override
    public AddWalletActivity provideAddWalletActivity() {
      return new AddWalletActivity(usersDao(), walletDao(), cryptoDao());
    }

    @Override
    public GetUserWalletActivity provideGetUserWalletActivity() {
      return new GetUserWalletActivity(usersDao(), walletDao(), cryptoDao());
    }

    @Override
    public GetUserOneWalletActivity provideGetUserOneWalletActivity() {
      return new GetUserOneWalletActivity(usersDao(), walletDao());
    }

    @Override
    public DeleteUserWalletActivity provideDeleteUserWalletActivity() {
      return new DeleteUserWalletActivity(usersDao(), walletDao());
    }

    @Override
    public UpdateWalletActivity provideUpdateWalletActivity() {
      return new UpdateWalletActivity(usersDao(), walletDao(), cryptoDao());
    }

    @Override
    public AddCryptoToWalletActivity provideAddCryptoToWalletActivity() {
      return new AddCryptoToWalletActivity(usersDao(), walletDao(), cryptoDao());
    }

    @Override
    public UpdateCryptoInWalletActivity provideUpdateCryptoInWalletActivity() {
      return new UpdateCryptoInWalletActivity(usersDao(), walletDao(), cryptoDao());
    }

    @Override
    public DeleteCryptoInWalletActivity provideDeleteCryptoInWalletActivity() {
      return new DeleteCryptoInWalletActivity(usersDao(), walletDao(), cryptoDao());
    }
  }
}
