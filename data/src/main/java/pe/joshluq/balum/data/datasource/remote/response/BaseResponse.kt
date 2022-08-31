package pe.joshluq.balum.data.datasource.remote.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class BaseResponse<T>(
    @JsonProperty("data") val data: T? = null,
    @JsonProperty("error") val error: ErrorResponse? = null,
)