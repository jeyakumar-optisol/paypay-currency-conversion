package io.paypay.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import io.paypay.base.FlowUseCase
import io.paypay.di.IoDispatcher
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.result.ErrorApiResult
import io.paypay.result.NetworkResult
import io.paypay.result.SuccessApiResult
import io.paypay.utility.nullablity.orElse
import io.paypay.utility.playservices.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val preferenceStorage: IPreferenceStorage
) : FlowUseCase<Unit, Pair<Boolean,String?>>() {

    override fun performAction(parameters: Unit): Flow<NetworkResult<Pair<Boolean,String?>>> {
        return flow {
            try {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val tokenResult = currentUser?.getIdToken(true)?.await().orElse { null }
                tokenResult?.token?.let {
                    preferenceStorage.saveLastUpdate(it)
                }
                emit(SuccessApiResult(Pair(tokenResult?.token != null, tokenResult?.token)))
            } catch (e: Exception) {
                emit(ErrorApiResult(e.localizedMessage ?: "unable to retrieve current user or token"))
            }
        }.flowOn(ioDispatcher)
    }


}