package io.digikraft.domain.usecase.token

import io.digikraft.base.FlowUseCase
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val preferenceStorage: IPreferenceStorage
) : FlowUseCase<Unit, String>() {

    override fun performAction(parameters: Unit): Flow<NetworkResult<String>> {
        return preferenceStorage.token.map { SuccessApiResult(it) }
    }

}