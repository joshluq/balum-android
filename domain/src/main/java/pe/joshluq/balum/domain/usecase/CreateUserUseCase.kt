package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.Profile
import pe.joshluq.balum.domain.repository.UserRepository
import pe.joshluq.balum.domain.validation.Field
import pe.joshluq.balum.domain.validation.ValidationResult
import pe.joshluq.balum.domain.validation.ifEmailError
import pe.joshluq.balum.domain.validation.ifPasswordError
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<CreateUserUseCase.Params, String> {

    override suspend fun invoke(params: Params) = withContext(Dispatchers.IO) {
        return@withContext try {
            validateProfile(params.profile)
            validateCredential(params.credential)
            userRepository.createUser(params.profile, params.credential).mapCatching { token ->
                token ?: throw NullPointerException()
            }
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }

    @Throws(ValidationResult::class)
    private fun validateProfile(profile: Profile) {
        if (profile.name.isBlank()) {
            throw ValidationResult.EmptyField(Field.NAME)
        }
        if (profile.lastName.isBlank()) {
            throw ValidationResult.EmptyField(Field.LAST_NAME)
        }
        profile.email.ifEmailError { error -> throw  error }
    }

    @Throws(ValidationResult::class)
    private fun validateCredential(credential: Credential) {
        credential.password.ifPasswordError { error -> throw  error }
    }

    class Params constructor(val profile: Profile, val credential: Credential)
}