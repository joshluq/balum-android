package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.repository.UserRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Credential, String> {

    override suspend fun invoke(params: Credential) = withContext(Dispatchers.IO) {
        userRepository.authenticate(params).mapCatching { token ->
            token ?: throw IllegalArgumentException()
        }
    }

}