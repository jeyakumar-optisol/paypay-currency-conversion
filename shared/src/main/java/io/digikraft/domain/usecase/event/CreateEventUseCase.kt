package io.digikraft.domain.usecase.event

import io.digikraft.base.UseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.model.event.create.CreateEventRequest
import io.digikraft.domain.model.event.create.CreateEventResponse
import io.digikraft.getResult
import io.digikraft.result.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val eventRepository: IEventRepository
) : UseCase<CreateEventRequest, CreateEventResponse>(ioDispatcher) {

    override suspend fun performAction(parameters: CreateEventRequest): NetworkResult<CreateEventResponse> {
        return getResult {
            eventRepository.createEvent(
                parameters
            )
        }
    }


}