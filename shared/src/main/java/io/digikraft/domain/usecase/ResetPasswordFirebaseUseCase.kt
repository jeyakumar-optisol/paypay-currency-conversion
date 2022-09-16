package io.digikraft.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import io.digikraft.base.FlowUseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.model.ResetPasswordModel
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.utility.playservices.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ResetPasswordFirebaseUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<ResetPasswordModel, Unit>() {

    override fun performAction(parameters: ResetPasswordModel): Flow<NetworkResult<Unit>> {
        return flow {
            try {
                FirebaseAuth.getInstance().sendPasswordResetEmail(parameters.email).await()
                emit(SuccessApiResult(Unit))
            } catch (e: Exception) {
                emit(ErrorApiResult(e.localizedMessage ?: "unable to authenticate with firebase"))
            }
        }.flowOn(ioDispatcher)
    }

}