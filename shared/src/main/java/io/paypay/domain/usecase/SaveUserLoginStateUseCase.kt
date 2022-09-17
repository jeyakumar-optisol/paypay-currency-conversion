package io.paypay.domain.usecase

import io.paypay.base.UseCase
import io.paypay.di.IoDispatcher
import io.paypay.domain.datasource.IPreferenceStorage
import io.paypay.result.NetworkResult
import io.paypay.result.SuccessApiResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveUserLoginStateUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val preferenceStorage: IPreferenceStorage
) : UseCase<Boolean, Unit>(ioDispatcher) {

    override suspend fun performAction(parameters: Boolean): NetworkResult<Unit> {
        return SuccessApiResult(preferenceStorage.saveToken(parameters.toString()))
    }


}