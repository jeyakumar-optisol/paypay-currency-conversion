package io.digikraft.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import io.digikraft.base.FlowUseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.model.signin.CreateFirebaseUserModel
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.utility.playservices.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CreateFirebaseUserUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FlowUseCase<CreateFirebaseUserModel, CreateFirebaseUserModel>() {

    override fun performAction(parameters: CreateFirebaseUserModel): Flow<NetworkResult<CreateFirebaseUserModel>> {
        return flow {
            try {
                val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    parameters.email,
                    parameters.password
                ).await()

                val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(parameters.username)
                    .build()
                authResult.user?.updateProfile(userProfileChangeRequest)?.await()

                emit(SuccessApiResult(parameters))
            } catch (e: Exception) {
                emit(ErrorApiResult(e.localizedMessage ?: "unable to authenticate with firebase"))
            }
        }.flowOn(ioDispatcher)
    }
}