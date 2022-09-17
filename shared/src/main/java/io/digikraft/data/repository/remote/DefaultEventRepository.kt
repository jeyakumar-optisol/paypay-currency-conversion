package io.digikraft.data.repository.remote

import io.digikraft.domain.model.event.create.CreateEventRequest
import io.digikraft.domain.model.event.create.CreateEventResponse
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.model.event.update.UpdateEventRequest
import io.digikraft.domain.datasource.services.EventApiService
import io.digikraft.domain.datasource.IEventRepository
import io.digikraft.domain.model.event.EventsResponse
import io.digikraft.domain.model.event.opening_hours.CreateEventOpeningHoursRequest
import io.digikraft.domain.model.event.opening_hours.CreateEventOpeningHoursResponse
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem
import io.digikraft.domain.model.ticket.TicketResponse

class DefaultEventRepository(private val eventApiService: EventApiService) : IEventRepository {

    override suspend fun createEvent(createEventRequest: CreateEventRequest): Result<CreateEventResponse> =
        eventApiService.createEvent(createEventRequest)

    override suspend fun fetchEvents(afterCursor: String): Result<EventsResponse> =
        eventApiService.fetchEvents(afterCursor)

    override suspend fun searchEvent(text: String): Result<ArrayList<EventItem>> =
        eventApiService.searchEvent(text)

    override suspend fun fetchEventById(eventId: Int): Result<EventItem> =
        eventApiService.fetchEventById(eventId)

    override suspend fun updateEvent(eventId: Int, updateEventRequest: UpdateEventRequest): Result<EventItem> =
        eventApiService.updateEvent(eventId, updateEventRequest)

    override suspend fun deleteEvent(eventId: Int): Result<Unit> =
        eventApiService.deleteEvent(eventId)

    override suspend fun createEventOpeningHours(
        token: String,
        eventId: Int,
        createEventOpeningHoursRequest: CreateEventOpeningHoursRequest
    ): Result<CreateEventOpeningHoursResponse> =
        eventApiService.createOpeningHoursForEvent(eventId, createEventOpeningHoursRequest)

    override suspend fun fetchEventOpeningHours(eventId: String): Result<ArrayList<EventOpeningHoursItem>> =
        eventApiService.fetchEventOpeningHours(eventId)

    override suspend fun fetchEventOpeningHourById(eventId: Int, openingHourId: Int): Result<EventOpeningHoursItem> =
        eventApiService.fetchEventOpeningHourById(eventId, openingHourId)

    override suspend fun deleteEventOpeningHours(eventId: Int, openingHourId: Int): Result<Unit> =
        eventApiService.deleteEventOpeningHours(eventId, openingHourId)

    override suspend fun updateEventOpeningHourById(
        eventId: Int,
        openingHourId: Int,
        eventOpeningHoursItem: EventOpeningHoursItem
    ): Result<EventOpeningHoursItem> =
        eventApiService.updateEventOpeningHourById(eventId, openingHourId, eventOpeningHoursItem)

    override suspend fun fetchEventTickets(eventId: Int, fromDate: String?, toDate: String?): Result<TicketResponse> =
        eventApiService.fetchEventTickets(eventId, fromDate, toDate)

}