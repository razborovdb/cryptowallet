package com.development.activity;

import com.development.dynamodb.ProjectsDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetProjectsActivity_Factory implements Factory<GetProjectsActivity> {
  private final Provider<ProjectsDao> projectsDaoProvider;

  public GetProjectsActivity_Factory(Provider<ProjectsDao> projectsDaoProvider) {
    this.projectsDaoProvider = projectsDaoProvider;
  }

  @Override
  public GetProjectsActivity get() {
    return new GetProjectsActivity(projectsDaoProvider.get());
  }

  public static GetProjectsActivity_Factory create(Provider<ProjectsDao> projectsDaoProvider) {
    return new GetProjectsActivity_Factory(projectsDaoProvider);
  }
}
