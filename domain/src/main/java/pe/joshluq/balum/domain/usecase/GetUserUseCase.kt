package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, User> {

    override suspend fun invoke(params: Unit) = withContext(Dispatchers.IO) {
        userRepository.getUser()
    }

}