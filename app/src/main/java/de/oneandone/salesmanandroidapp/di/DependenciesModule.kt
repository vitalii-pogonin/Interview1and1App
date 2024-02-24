package de.oneandone.salesmanandroidapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanListUseCase
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanListUseCaseImpl
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanRepository
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
object DependenciesModule {

    @Provides
    fun provideRepository(): SalesmanRepository = SalesmanRepositoryImpl()

    @Provides
    fun provideUseCase(repository: SalesmanRepository): SalesmanListUseCase = SalesmanListUseCaseImpl(repository)
}
