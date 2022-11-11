package pe.joshluq.balum.data.datasource.remote.mapper

import pe.joshluq.balum.data.datasource.remote.response.user.UserResponse
import pe.joshluq.balum.domain.model.Profile
import pe.joshluq.balum.domain.model.User
import kotlin.jvm.Throws

@Throws
fun mapToUser(response: UserResponse) = User(
    id = response.id ?: throw IllegalArgumentException(),
    profile = Profile(
        name = response.name ?: String(),
        lastName = response.lastname ?: String(),
        email = response.username ?: throw IllegalArgumentException()
    ),
)