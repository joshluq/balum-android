package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.Profile
import pe.joshluq.balum.domain.model.User
import javax.inject.Inject
import javax.inject.Named

class SignUpUseCase @Inject constructor(
    @Named("createUserUseCase")  private val createUserUseCase: UseCase<CreateUserUseCase.Params, String>,
    @Named("getUserUseCase") private val getUserUseCase: UseCase<Unit, User>,
) : UseCase<SignUpUseCase.Params, User> {

    override suspend fun invoke(params: Params) = withContext(Dispatchers.IO) {
        return@withContext try {
            createUserUseCase(
                CreateUserUseCase.Params(params.profile, params.credential)
            ).getOrThrow()
            getUserUseCase(Unit)
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }

    class Params constructor(val profile: Profile, val credential: Credential)
}