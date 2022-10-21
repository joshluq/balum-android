package pe.joshluq.balum.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, User> {

    override suspend fun invoke(params: Unit) = withContext(Dispatchers.IO) {
        return@withContext try {
            userRepository.getUser().mapCatching { user -> user ?: throw NullPointerException() }
        } catch (validationResult: Throwable) {
            Result.failure(validationResult)
        }
    }
}