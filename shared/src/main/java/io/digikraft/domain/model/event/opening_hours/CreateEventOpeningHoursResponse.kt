package io.digikraft.domain.model.event.opening_hours

data class CreateEventOpeningHoursResponse(
    val date: String,
    val eventId: Int,
    val timeFrom: String,
    val timeTo: String,
    val weekday: String
)