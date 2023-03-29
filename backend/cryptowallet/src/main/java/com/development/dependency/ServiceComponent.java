package com.development.dependency;

import com.development.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DaoModule.class })
/**
 * ServiceComponent.
 */
public interface ServiceComponent {
    /**
     * provideGetProjectsActivity.
     * @return - GetProjectsActivity
     */
    GetProjectsActivity provideGetProjectsActivity();

    CreateUserActivity provideCreateUserActivity();
    LoginUserActivity provideLoginUserActivity();
    GetUserActivity provideGetUserActivity();
    GetAvailableCryptoActivity provideGetAvailableCryptoActivity();
    UpdateAvailableCryptoActivity provideUpdateAvailableCryptoActivity();
    AddCryptoToAvailableCryptoActivity provideAddCryptoToAvailableCryptoActivity();
    GetCryptoFromAvailableCryptoActivity provideGetCryptoFromAvailableCryptoActivity();
    UpdateCryptoInAvailableCryptoActivity provideUpdateCryptoInAvailableCryptoActivity();
    DeleteCryptoInAvailableCryptoActivity provideDeleteCryptoInAvailableCryptoActivity();
    AddWalletActivity provideAddWalletActivity();
    GetUserWalletActivity provideGetUserWalletActivity();
    GetUserOneWalletActivity provideGetUserOneWalletActivity();
    DeleteUserWalletActivity provideDeleteUserWalletActivity();
    UpdateWalletActivity provideUpdateWalletActivity();
    AddCryptoToWalletActivity provideAddCryptoToWalletActivity();
    UpdateCryptoInWalletActivity provideUpdateCryptoInWalletActivity();
    DeleteCryptoInWalletActivity provideDeleteCryptoInWalletActivity();

}
