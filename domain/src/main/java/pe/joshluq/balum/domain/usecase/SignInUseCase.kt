package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
) : UseCase<Credential, User> {

    override suspend fun invoke(params: Credential) = withContext(Dispatchers.IO) {
        authenticateUserUseCase(params).getOrThrow()
        getUserUseCase(Unit)
    }
}