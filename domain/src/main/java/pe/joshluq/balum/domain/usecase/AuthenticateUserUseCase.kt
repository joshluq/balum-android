package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.repository.UserRepository
import pe.joshluq.balum.domain.validation.ValidationResult
import pe.joshluq.balum.domain.validation.ifEmailError
import pe.joshluq.balum.domain.validation.ifPasswordError
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<AuthenticateUserUseCase.Params, String> {

    override suspend fun invoke(params: Params) = withContext(Dispatchers.IO) {
        return@withContext try {
            validateCredential(params.credential)
            userRepository.authenticateUser(params.credential).mapCatching { token ->
                token ?: throw NullPointerException()
            }
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }

    @Throws(ValidationResult::class)
    private fun validateCredential(credential: Credential) {
        credential.username.ifEmailError { error -> throw  error }
        credential.password.ifPasswordError { error -> throw  error }
    }

    class Params  constructor(val credential: Credential)
}