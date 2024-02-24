package de.oneandone.salesmanandroidapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import de.oneandone.salesmanandroidapp.list.models.SalesmanDto
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanListUseCase
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanListUseCaseImpl
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanRepository
import de.oneandone.salesmanandroidapp.list.usecase.SalesmanRepositoryRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.realmListOf

@Module
@InstallIn(ActivityRetainedComponent::class)
object DependenciesModule {

    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(SalesmanDto::class)
        )
            .inMemory()
            .initialData {
                copyToRealm(SalesmanDto().apply {
                    name = "Artem Titarenko"
                    areas = realmListOf("76133")
                })
                copyToRealm(SalesmanDto().apply {
                    name = "Bernd Schmitt"
                    areas = realmListOf("7619*")
                })
                copyToRealm(SalesmanDto().apply {
                    name = "Chris Krapp"
                    areas = realmListOf("762*")
                })
                copyToRealm(SalesmanDto().apply {
                    name = "Alex Uber"
                    areas = realmListOf("86*")
                })
            }
            .build()
        return Realm.open(config)
    }

    @Provides
    fun provideRepository(realm: Realm): SalesmanRepository = SalesmanRepositoryRealm(realm)

    @Provides
    fun provideUseCase(repository: SalesmanRepository): SalesmanListUseCase = SalesmanListUseCaseImpl(repository)
}
