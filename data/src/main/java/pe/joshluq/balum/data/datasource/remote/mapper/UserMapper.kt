package pe.joshluq.balum.data.datasource.remote.mapper

import pe.joshluq.balum.data.datasource.remote.response.user.UserResponse
import pe.joshluq.balum.domain.model.User
import kotlin.jvm.Throws

@Throws
fun mapToUser(response: UserResponse) = User(
    id = response.id ?: throw IllegalArgumentException(),
    username = response.username ?: throw IllegalArgumentException(),
)