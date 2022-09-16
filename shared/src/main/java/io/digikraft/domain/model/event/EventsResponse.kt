package io.digikraft.domain.model.event

import io.digikraft.domain.model.Cursor
import io.digikraft.domain.model.event.item.EventItem

data class EventsResponse(
    val data: ArrayList<EventItem>? = null,
    val cursor: Cursor? = null
)