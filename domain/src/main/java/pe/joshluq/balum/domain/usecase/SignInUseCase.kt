package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.validation.ValidationResult

import pe.joshluq.balum.domain.validation.ifEmailError
import pe.joshluq.balum.domain.validation.ifPasswordError
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticateUserUseCase: AuthenticateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
) : UseCase<Credential, User> {

    override suspend fun invoke(params: Credential) = withContext(Dispatchers.IO) {
        return@withContext try {
            validateEmail(params)
            validatePassword(params)
            authenticateUserUseCase(params).getOrThrow()
            getUserUseCase(Unit)
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }

    @Throws(ValidationResult::class)
    private fun validateEmail(credential: Credential) {
        credential.username.ifEmailError { error -> throw  error }
    }

    @Throws(ValidationResult::class)
    private fun validatePassword(credential: Credential) {
        credential.password.ifPasswordError { error -> throw  error }
    }

}