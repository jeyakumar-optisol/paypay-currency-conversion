package io.digikraft.domain.usecase

import io.digikraft.base.FlowUseCase
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserLoginStateUseCase @Inject constructor(
    private val preferenceStorage: IPreferenceStorage
) : FlowUseCase<Unit, Boolean>() {

    override fun performAction(parameters: Unit): Flow<NetworkResult<Boolean>> {
        return flow { emit(SuccessApiResult(preferenceStorage.logged.first())) }
    }


}