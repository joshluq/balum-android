package pe.joshluq.balum.data.datasource.remote.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorResponse(
    @JsonProperty("code") val code: String?,
    @JsonProperty("message") val message: String?,
)