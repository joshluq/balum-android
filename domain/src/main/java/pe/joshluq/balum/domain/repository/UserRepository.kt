package pe.joshluq.balum.domain.repository

import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.Profile
import pe.joshluq.balum.domain.model.User


interface UserRepository {

    suspend fun authenticate(credentials: Credential): Result<String?>

    suspend fun create(profile: Profile,credentials: Credential): Result<String?>

    suspend fun getUser(): Result<User?>

}