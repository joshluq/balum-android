package pe.joshluq.balum.data.datasource.remote.response.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SignInResponse(
    @JsonProperty("token") val token: String?,
)