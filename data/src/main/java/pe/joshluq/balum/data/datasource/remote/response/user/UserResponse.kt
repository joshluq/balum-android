package pe.joshluq.balum.data.datasource.remote.response.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class UserResponse(
    @JsonProperty("id") val id: String?,
    @JsonProperty("username") val username: String?,
)