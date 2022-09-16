package io.digikraft.domain.datasource

import io.digikraft.domain.model.event.EventsResponse
import io.digikraft.domain.model.event.create.CreateEventRequest
import io.digikraft.domain.model.event.create.CreateEventResponse
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.model.event.opening_hours.CreateEventOpeningHoursRequest
import io.digikraft.domain.model.event.opening_hours.CreateEventOpeningHoursResponse
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem
import io.digikraft.domain.model.event.update.UpdateEventRequest
import io.digikraft.domain.model.ticket.TicketResponse

interface IEventRepository {

    suspend fun createEvent(token: String, createEventRequest: CreateEventRequest): Result<CreateEventResponse>

    suspend fun fetchEvents(token: String, afterCursor: String): Result<EventsResponse>

    suspend fun searchEvent(token: String, text: String): Result<ArrayList<EventItem>>

    suspend fun fetchEventById(token: String, eventId: Int): Result<EventItem>

    suspend fun updateEvent(token: String, eventId: Int, updateEventRequest: UpdateEventRequest): Result<EventItem>

    suspend fun deleteEvent(token: String, eventId: Int): Result<Unit>

    suspend fun createEventOpeningHours(
        token: String,
        eventId: Int,
        createEventOpeningHoursRequest: CreateEventOpeningHoursRequest
    ): Result<CreateEventOpeningHoursResponse>

    suspend fun fetchEventOpeningHours(token: String, eventId: String): Result<ArrayList<EventOpeningHoursItem>>

    suspend fun fetchEventOpeningHourById(token: String, eventId: Int, openingHourId: Int): Result<EventOpeningHoursItem>

    suspend fun deleteEventOpeningHours(token: String, eventId: Int, openingHourId: Int): Result<Unit>

    suspend fun updateEventOpeningHourById(
        token: String,
        eventId: Int,
        openingHourId: Int,
        eventOpeningHoursItem: EventOpeningHoursItem
    ): Result<EventOpeningHoursItem>

    suspend fun fetchEventTickets(token: String, eventId: Int, fromDate: String?, toDate: String?): Result<TicketResponse>

}