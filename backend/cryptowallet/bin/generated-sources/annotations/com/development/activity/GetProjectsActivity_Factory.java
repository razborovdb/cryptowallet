package com.development.activity;

import com.development.dynamodb.ProjectsDao;
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
public final class GetProjectsActivity_Factory implements Factory<GetProjectsActivity> {
  private final Provider<ProjectsDao> projectsDaoProvider;

  public GetProjectsActivity_Factory(Provider<ProjectsDao> projectsDaoProvider) {
    this.projectsDaoProvider = projectsDaoProvider;
  }

  @Override
  public GetProjectsActivity get() {
    return newInstance(projectsDaoProvider.get());
  }

  public static GetProjectsActivity_Factory create(Provider<ProjectsDao> projectsDaoProvider) {
    return new GetProjectsActivity_Factory(projectsDaoProvider);
  }

  public static GetProjectsActivity newInstance(ProjectsDao projectsDao) {
    return new GetProjectsActivity(projectsDao);
  }
}
