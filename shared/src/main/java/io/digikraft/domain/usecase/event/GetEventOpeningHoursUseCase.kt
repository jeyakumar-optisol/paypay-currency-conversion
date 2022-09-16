package io.digikraft.domain.usecase.event

import io.digikraft.base.UseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.datasource.IPreferenceStorage
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem
import io.digikraft.getResult
import io.digikraft.result.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetEventOpeningHoursUseCase @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val preferenceStorage: IPreferenceStorage,
    private val eventRepository: IEventRepository
) : UseCase<String, ArrayList<EventOpeningHoursItem>>(ioDispatcher) {

    override suspend fun performAction(parameters: String): NetworkResult<ArrayList<EventOpeningHoursItem>> {
        return getResult {
            eventRepository.fetchEventOpeningHours(
                preferenceStorage.token.first(),
                parameters
            )
        }
    }

}