package pe.joshluq.balum.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.joshluq.balum.data.di.RestApiModule
import pe.joshluq.balum.data.repository.DataUserRepository
import pe.joshluq.balum.domain.repository.UserRepository
import javax.inject.Singleton

@Module(includes = [RestApiModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: DataUserRepository): UserRepository

}