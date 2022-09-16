package io.digikraft.data.utility.interceptor

import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.usecase.RefreshTokenUseCase
import io.digikraft.result.SuccessApiResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.commonRemoveHeader

class AuthorizationInterceptor(
    private val storage: IPreferenceStorage,
    private val refreshTokenUseCase: RefreshTokenUseCase
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainRequest = chain.request()
        val token = runBlocking {
            return@runBlocking storage.token.firstOrNull()
        } ?: error("token couldnt be null from datapreference")

        var initialRequest = chainRequest.updateHeader(CONST_HEADER_AUTHORIZATION, token.asToken())

        val initialResponse = chain.proceed(initialRequest)
        val submitResponse = if (initialResponse.code == ServerCode.TOKEN_EXPIRED.code) {
            return runBlocking {
                val (success, token) = refreshTokenUseCase(Unit).map { result -> (result as SuccessApiResult).data }
                    .first()
                if (success) {
                    initialResponse.close()
                    initialRequest = initialRequest.updateHeader(
                        CONST_HEADER_AUTHORIZATION,
                        token?.asToken() ?: error("usecase returning value cannot be null, token")
                    )
                    chain.proceed(initialRequest)
                } else {
                    initialResponse
                }
            }
        }else{
            initialResponse
        }
        return submitResponse
    }
}

const val CONST_HEADER_AUTHORIZATION = "Authorization"

fun Request.updateHeader(key: String, value: String): Request {
    return newBuilder()
        .commonRemoveHeader(key)
        .headers(mutableMapOf(key to value).toHeaders())
        .build()
}

fun String.asToken(): String {
    return "Bearer $this"
}