package com.development.dependency;

import com.development.activity.CreateUserActivity;
import com.development.activity.GetProjectsActivity;
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

}
