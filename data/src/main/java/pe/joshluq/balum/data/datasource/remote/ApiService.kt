package pe.joshluq.balum.data.datasource.remote

import kotlinx.coroutines.Deferred
import pe.joshluq.balum.data.datasource.remote.request.user.SignInRequest
import pe.joshluq.balum.data.datasource.remote.response.BaseResponse
import pe.joshluq.balum.data.datasource.remote.response.user.SignInResponse
import pe.joshluq.balum.data.datasource.remote.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("user/sign-in")
    fun authenticateAsync(@Body body: SignInRequest): Deferred<Response<BaseResponse<SignInResponse>>>

    @GET("user")
    fun getUserAsync(): Deferred<Response<BaseResponse<UserResponse>>>

}