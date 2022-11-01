package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.repository.UserRepository
import pe.joshluq.balum.domain.validation.ValidationResult
import pe.joshluq.balum.domain.validation.ifEmailError
import javax.inject.Inject

class RecoverCredentialUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<RecoverCredentialUseCase.Params, Unit> {

    override suspend fun invoke(params: Params): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext try {
            validateEmail(params.email)
            userRepository.recoverCredential(params.email)
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }

    @Throws(ValidationResult::class)
    private fun validateEmail(email: String) {
        email.ifEmailError { error -> throw  error }
    }

    class Params constructor(val email: String)

}