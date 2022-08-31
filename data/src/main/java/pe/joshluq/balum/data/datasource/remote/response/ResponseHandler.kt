package pe.joshluq.balum.data.datasource.remote.response

import kotlinx.coroutines.Deferred
import retrofit2.Response

suspend fun <T> handleResponse(
    function: () -> Deferred<Response<BaseResponse<T>>>,
) = try {

    val result = function().await()

    when {
        result.isSuccessful.not() -> {
            Result.failure(Throwable(""))
        }
        result.body()?.error != null -> {
            Result.failure(Throwable(result.body()!!.error!!.message))
        }
        result.body()?.data != null -> {
            Result.success(result.body()!!.data!!)
        }
        else -> {
            Result.failure(Throwable(""))
        }
    }

} catch (e: Exception) {
    Result.failure(e)
}