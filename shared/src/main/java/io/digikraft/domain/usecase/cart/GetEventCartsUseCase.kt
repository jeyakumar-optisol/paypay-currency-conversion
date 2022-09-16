package io.digikraft.domain.usecase.cart

import io.digikraft.base.FlowUseCaseDatabase
import io.digikraft.domain.datasource.IRoomDatabaseRepository
import io.digikraft.domain.model.cart.CartEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventCartsUseCase @Inject constructor(private val roomRepository: IRoomDatabaseRepository) : FlowUseCaseDatabase<Int, List<CartEntity>>() {

    override fun performAction(parameters: Int): Flow<List<CartEntity>> {
        return roomRepository.cartDao.cartEventFlow(parameters)
    }

}