package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import javax.inject.Inject
import javax.inject.Named

class SignInUseCase @Inject constructor(
    @Named("authenticateUserUseCase") private val authenticateUserUseCase: UseCase<AuthenticateUserUseCase.Params, String>,
    @Named("getUserUseCase") private val getUserUseCase: UseCase<Unit, User>,
) : UseCase<SignInUseCase.Params, User> {

    override suspend fun invoke(params: Params) = withContext(Dispatchers.IO) {
        return@withContext try {
            authenticateUserUseCase(AuthenticateUserUseCase.Params(params.credential)).getOrThrow()
            getUserUseCase(Unit)
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }

    class Params constructor(val credential: Credential)
}