package io.digikraft.domain.datasource.services

import io.digikraft.domain.model.event.EventsResponse
import io.digikraft.domain.model.event.create.CreateEventRequest
import io.digikraft.domain.model.event.create.CreateEventResponse
import io.digikraft.domain.model.event.item.EventItem
import io.digikraft.domain.model.event.opening_hours.CreateEventOpeningHoursRequest
import io.digikraft.domain.model.event.opening_hours.CreateEventOpeningHoursResponse
import io.digikraft.domain.model.event.opening_hours.EventOpeningHoursItem
import io.digikraft.domain.model.event.update.UpdateEventRequest
import io.digikraft.domain.model.ticket.TicketResponse
import retrofit2.http.*

interface EventApiService {

    @POST("v1/events")
    suspend fun createEvent(@Header("Authorization") token: String, @Body createEventRequest: CreateEventRequest): Result<CreateEventResponse>

    @GET("v1/events")
    suspend fun fetchEvents(@Header("Authorization") token: String, @Query("afterCursor") afterCursor: String): Result<EventsResponse>

    @GET("v1/events/search")
    suspend fun searchEvent(@Header("Authorization") token: String, @Query("text") text: String): Result<ArrayList<EventItem>>

    @GET("v1/events/{eventId}")
    suspend fun fetchEventById(@Header("Authorization") token: String, @Path("eventId") eventId: Int): Result<EventItem>

    @PATCH("v1/events/{eventId}")
    suspend fun updateEvent(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: Int,
        @Body updateEventRequest: UpdateEventRequest
    ): Result<EventItem>

    @DELETE("v1/events/{eventId}")
    suspend fun deleteEvent(@Header("Authorization") token: String, @Path("eventId") eventId: Int): Result<Unit>

    @POST("v1/events/{eventId}/opening-hours")
    suspend fun createOpeningHoursForEvent(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: Int,
        @Body createEventOpeningHoursRequest: CreateEventOpeningHoursRequest
    ): Result<CreateEventOpeningHoursResponse>

    @GET("v1/events/{eventId}/opening-hours")
    suspend fun fetchEventOpeningHours(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: String
    ): Result<ArrayList<EventOpeningHoursItem>>

    @GET("v1/events/{eventId}/opening-hours/{openingHourId}")
    suspend fun fetchEventOpeningHourById(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: Int,
        @Path("openingHourId") openingHourId: Int
    ): Result<EventOpeningHoursItem>

    @PATCH("v1/events/{eventId}/opening-hours/{openingHourId}")
    suspend fun updateEventOpeningHourById(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: Int,
        @Path("openingHourId") openingHourId: Int,
        @Body eventOpeningHoursItem: EventOpeningHoursItem
    ): Result<EventOpeningHoursItem>

    @DELETE("v1/events/{eventId}/opening-hours/{openingHourId}")
    suspend fun deleteEventOpeningHours(
        @Header("Authorization") token: String,
        @Path("eventId") eventId: Int,
        @Path("openingHourId") openingHourId: Int,
    ): Result<Unit>

    @GET("v1/tickets")
    suspend fun fetchEventTickets(
        @Header("Authorization") token: String,
        @Query("eventId") eventId: Int,
        @Query("fromDate") fromDate: String?,
        @Query("toDate") toDate: String?,
    ): Result<TicketResponse>

}