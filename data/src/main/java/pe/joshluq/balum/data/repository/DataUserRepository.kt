package pe.joshluq.balum.data.repository


import pe.joshluq.balum.data.datasource.remote.ApiService
import pe.joshluq.balum.data.datasource.remote.mapper.mapToUser
import pe.joshluq.balum.data.datasource.remote.request.user.SignInRequest
import pe.joshluq.balum.data.datasource.remote.response.handleResponse
import pe.joshluq.balum.data.datasource.remote.response.user.SignInResponse
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.repository.UserRepository
import javax.inject.Inject

class DataUserRepository @Inject constructor(
    private val api: ApiService,
) : UserRepository {

    override suspend fun authenticate(credentials: Credential) = handleResponse {
        api.authenticateAsync(SignInRequest(credentials.username, credentials.password))
    }.map(SignInResponse::token)

    override suspend fun getUser(): Result<User> = handleResponse {
        api.getUserAsync()
    }.mapCatching(::mapToUser)

}