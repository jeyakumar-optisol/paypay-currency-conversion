package io.digikraft.domain.usecase.event

import io.digikraft.base.UseCase
import io.digikraft.di.IoDispatcher
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.model.ticket.TicketItem
import io.digikraft.domain.model.ticket.TicketResponse
import io.digikraft.domain.usecase.cart.GetEventCartsUseCase
import io.digikraft.getResult
import io.digikraft.result.ErrorApiResult
import io.digikraft.result.NetworkResult
import io.digikraft.result.SuccessApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetEventTicketsUseCase @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val eventRepository: IEventRepository,
    private val getEventCartsUseCase: GetEventCartsUseCase,
) : UseCase<Triple<Int, String?, String?>, TicketResponse>(ioDispatcher) {

    override suspend fun performAction(parameters: Triple<Int, String?, String?>): NetworkResult<TicketResponse> {
        val result = getResult {
            eventRepository.fetchEventTickets(
                parameters.first,
                parameters.second,
                parameters.third
            )
        }
        return when (result) {
            is SuccessApiResult -> {
                val list = result.data.data ?: ArrayList()
                val cartList = getEventCartsUseCase.invoke(parameters.first).first()
                for (ticketItem in list) {
                    ticketItem.cartEntity = cartList.firstOrNull { ticketItem.id == it.id }
                }
                list.add(0, TicketItem(id = 0, view_holder_type = 0, name = "Popular"))
                SuccessApiResult(TicketResponse(list))
            }
            is ErrorApiResult -> {
                result
            }
        }
    }
}