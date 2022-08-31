package pe.joshluq.balum.data.datasource.remote.request.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SignInRequest(
    @JsonProperty("email") val username: String,
    @JsonProperty("password") val password: String,
)