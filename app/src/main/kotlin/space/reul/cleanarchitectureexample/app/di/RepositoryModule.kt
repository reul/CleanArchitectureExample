package space.reul.cleanarchitectureexample.app.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import space.reul.cleanarchitectureexample.data.repository.CatsRepositoryImpl
import space.reul.cleanarchitectureexample.domain.usecase.ListCats
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryInterfacesModule {
    @Singleton
    @Binds
    abstract fun bindListCatRepository(repositoryImpl: CatsRepositoryImpl): ListCats.Repository
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryInstancesModule {
    @Singleton
    @Provides
    fun providesCatRepositoryImpl(): CatsRepositoryImpl = CatsRepositoryImpl()
}
