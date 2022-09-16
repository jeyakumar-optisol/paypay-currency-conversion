package io.digikraft.domain.usecase.sign_in

import com.google.firebase.auth.FirebaseAuth
import io.digikraft.base.FlowUseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.model.signin.GoogleAuthenticationModel
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import io.digikraft.utility.debug.Log
import io.digikraft.utility.playservices.await
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInWithGoogleAuthenticationFirebaseUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val preferenceStorage: IPreferenceStorage
) : FlowUseCase<GoogleAuthenticationModel, String>() {

    override fun performAction(parameters: GoogleAuthenticationModel): Flow<NetworkResult<String>> {
        return flow {
            try {
                val authResult = FirebaseAuth.getInstance().signInWithCredential(parameters.authCredential!!).await()

                val token = authResult.user?.getIdToken(true)?.await()?.token
                    ?: error("token not to be null")

                Log.e("JeyK", "token $token")
                preferenceStorage.saveToken(token)
                emit(SuccessApiResult(token))
            } catch (e: Exception) {
                emit(ErrorApiResult(e.localizedMessage ?: "unable to authenticate with firebase"))
            }
        }.flowOn(ioDispatcher)
    }

}