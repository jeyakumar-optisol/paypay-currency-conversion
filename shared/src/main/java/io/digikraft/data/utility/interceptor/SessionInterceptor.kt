package io.digikraft.data.utility.interceptor

import io.digikraft.domain.usecase.RefreshTokenUseCase
import io.digikraft.result.SuccessApiResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class SessionInterceptor(private val refreshTokenUseCase: RefreshTokenUseCase) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return if (response.code == ServerCode.TOKEN_EXPIRED.code) {
            return runBlocking {
                return@runBlocking response
                val (success, token) = refreshTokenUseCase(Unit).map { result -> (result as SuccessApiResult).data }
                    .first()
                return@runBlocking if (success) {
                    response.close()
                    request.newBuilder()
                        .header("Authorization", token ?: error("token couldnt be null"))
                        .build()
                    chain.proceed(request)
                } else {
                    //todo: session out
                    response
                }
            }
        } else {
            response
        }
    }
}

enum class ServerCode(val code: Int) {
    TOKEN_EXPIRED(401)
}