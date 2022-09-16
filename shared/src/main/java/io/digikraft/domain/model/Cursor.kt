package io.digikraft.domain.model

data class Cursor(
    val afterCursor: String,
    val beforeCursor: String
)