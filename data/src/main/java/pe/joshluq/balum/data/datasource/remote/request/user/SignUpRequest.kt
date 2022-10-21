package pe.joshluq.balum.data.datasource.remote.request.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class SignUpRequest(
    @JsonProperty("name") val name: String,
    @JsonProperty("last_name") val lastName: String,
    @JsonProperty("email") val username: String,
    @JsonProperty("password") val password: String,
)