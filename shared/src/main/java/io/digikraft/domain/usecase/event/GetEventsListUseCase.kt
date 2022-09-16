package io.digikraft.domain.usecase.event

import io.digikraft.base.FlowUseCase
import io.digikraft.base.UseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.model.event.EventsResponse
import io.digikraft.getResult
import io.digikraft.result.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetEventsListUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: IEventRepository,
    private val preferenceStorage: IPreferenceStorage
) : FlowUseCase<String, EventsResponse>() {

    override fun performAction(parameters: String): Flow<NetworkResult<EventsResponse>> {
        return flow { emit(getResult { repository.fetchEvents(preferenceStorage.token.first(), parameters) }) }.flowOn(ioDispatcher)
    }

}