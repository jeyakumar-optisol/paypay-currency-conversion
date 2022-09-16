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

    override suspend fun createEvent(token: String, createEventRequest: CreateEventRequest): Result<CreateEventResponse> =
        eventApiService.createEvent(token, createEventRequest)

    override suspend fun fetchEvents(token: String, afterCursor: String): Result<EventsResponse> =
        eventApiService.fetchEvents(token, afterCursor)

    override suspend fun searchEvent(token: String, text: String): Result<ArrayList<EventItem>> =
        eventApiService.searchEvent(token, text)

    override suspend fun fetchEventById(token: String, eventId: Int): Result<EventItem> =
        eventApiService.fetchEventById(token, eventId)

    override suspend fun updateEvent(token: String, eventId: Int, updateEventRequest: UpdateEventRequest): Result<EventItem> =
        eventApiService.updateEvent(token, eventId, updateEventRequest)

    override suspend fun deleteEvent(token: String, eventId: Int): Result<Unit> =
        eventApiService.deleteEvent(token, eventId)

    override suspend fun createEventOpeningHours(
        token: String,
        eventId: Int,
        createEventOpeningHoursRequest: CreateEventOpeningHoursRequest
    ): Result<CreateEventOpeningHoursResponse> =
        eventApiService.createOpeningHoursForEvent(token, eventId, createEventOpeningHoursRequest)

    override suspend fun fetchEventOpeningHours(token: String, eventId: String): Result<ArrayList<EventOpeningHoursItem>> =
        eventApiService.fetchEventOpeningHours(token, eventId)

    override suspend fun fetchEventOpeningHourById(token: String, eventId: Int, openingHourId: Int): Result<EventOpeningHoursItem> =
        eventApiService.fetchEventOpeningHourById(token, eventId, openingHourId)

    override suspend fun deleteEventOpeningHours(token: String, eventId: Int, openingHourId: Int): Result<Unit> =
        eventApiService.deleteEventOpeningHours(token, eventId, openingHourId)

    override suspend fun updateEventOpeningHourById(
        token: String,
        eventId: Int,
        openingHourId: Int,
        eventOpeningHoursItem: EventOpeningHoursItem
    ): Result<EventOpeningHoursItem> =
        eventApiService.updateEventOpeningHourById(token, eventId, openingHourId, eventOpeningHoursItem)

    override suspend fun fetchEventTickets(token: String, eventId: Int, fromDate: String?, toDate: String?): Result<TicketResponse> =
        eventApiService.fetchEventTickets(token, eventId, fromDate, toDate)

}