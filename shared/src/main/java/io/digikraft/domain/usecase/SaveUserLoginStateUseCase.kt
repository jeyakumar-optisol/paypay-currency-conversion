package io.digikraft.domain.usecase

import io.digikraft.base.UseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveUserLoginStateUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val preferenceStorage: IPreferenceStorage
) : UseCase<Boolean, Unit>(ioDispatcher) {

    override suspend fun performAction(parameters: Boolean): NetworkResult<Unit> {
        return SuccessApiResult(preferenceStorage.saveLoginState(parameters))
    }


}