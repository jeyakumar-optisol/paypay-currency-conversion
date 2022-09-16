package io.digikraft.domain.usecase.cart

import io.digikraft.base.FlowUseCaseDatabase
import io.digikraft.domain.datasource.IRoomDatabaseRepository
import io.digikraft.domain.model.cart.CartEntity
import io.digikraft.domain.model.ticket.TicketItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddCartTicketUseCase @Inject constructor(
    private val roomRepository: IRoomDatabaseRepository
) : FlowUseCaseDatabase<Pair<TicketItem, Int>, Long>() {

    override fun performAction(parameters: Pair<TicketItem, Int>): Flow<Long> {
        return flow {
            emit(roomRepository.cartDao.insert(
                (parameters.first.cartEntity ?: CartEntity().apply {
                    this.id = parameters.first.id
                    this.eventId = parameters.first.eventId
                }).apply {
                    this.quantity = parameters.second
                }
            ))
        }
    }


}