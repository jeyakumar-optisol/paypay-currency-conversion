package io.digikraft.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import io.digikraft.base.FlowUseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.utility.nullablity.orElse
import io.digikraft.utility.playservices.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserLoginStateUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val preferenceStorage: IPreferenceStorage
) : FlowUseCase<Unit, Boolean>() {

    override fun performAction(parameters: Unit): Flow<NetworkResult<Boolean>> {
        return flow {
            try {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val tokenResult = currentUser?.getIdToken(true)?.await().orElse { null }
                tokenResult?.token?.let {
                    preferenceStorage.saveToken(it)
                }
                emit(SuccessApiResult(tokenResult?.token != null))
            } catch (e: Exception) {
                emit(ErrorApiResult(e.localizedMessage ?: "unable to retrieve current user or token"))
            }
        }.flowOn(ioDispatcher)
    }


}