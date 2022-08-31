package pe.joshluq.balum.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.usecase.AuthenticateUserUseCase
import pe.joshluq.balum.domain.usecase.GetUserUseCase
import pe.joshluq.balum.domain.usecase.SignInUseCase
import pe.joshluq.balum.domain.usecase.UseCase
import javax.inject.Named

import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    @Named("signInUseCase")
    abstract fun bindSignInUseCase(useCase: SignInUseCase): UseCase<Credential, User>

    @Singleton
    @Binds
    @Named("authenticateUserUseCase")
    abstract fun bindAuthenticateUserUseCase(authenticateUserUseCase: AuthenticateUserUseCase): UseCase<Credential, String>

    @Singleton
    @Binds
    @Named("getUserUseCase")
    abstract fun bindGetUserUseCase(getUserUseCase: GetUserUseCase): UseCase<Unit, User>
}