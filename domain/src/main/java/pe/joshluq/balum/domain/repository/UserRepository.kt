package pe.joshluq.balum.domain.repository

import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.Profile
import pe.joshluq.balum.domain.model.User

interface UserRepository {

    suspend fun authenticateUser(credentials: Credential): Result<String?>

    suspend fun createUser(profile: Profile, credentials: Credential): Result<String?>

    suspend fun getUser(): Result<User?>

    suspend fun recoverCredential(email :String): Result<Unit>

}