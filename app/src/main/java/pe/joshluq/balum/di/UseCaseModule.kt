package pe.joshluq.balum.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.usecase.*
import javax.inject.Named

import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    @Named("signInUseCase")
    abstract fun bindSignInUseCase(useCase: SignInUseCase): UseCase<SignInUseCase.Params, User>

    @Singleton
    @Binds
    @Named("authenticateUserUseCase")
    abstract fun bindAuthenticateUserUseCase(authenticateUserUseCase: AuthenticateUserUseCase): UseCase<AuthenticateUserUseCase.Params, String>

    @Singleton
    @Binds
    @Named("getUserUseCase")
    abstract fun bindGetUserUseCase(getUserUseCase: GetUserUseCase): UseCase<Unit, User>

    @Singleton
    @Binds
    @Named("createUserUseCase")
    abstract fun bindCreateUserUseCase(createUserUseCase: CreateUserUseCase): UseCase<CreateUserUseCase.Params, String>

    @Singleton
    @Binds
    @Named("signUpUseCase")
    abstract fun bindSignUpUseCase(useCase: SignUpUseCase): UseCase<SignUpUseCase.Params, User>

    @Singleton
    @Binds
    @Named("recoverCredentialUseCase")
    abstract fun bindRecoverCredentialUseCase(useCase: RecoverCredentialUseCase): UseCase<RecoverCredentialUseCase.Params, Unit>

}