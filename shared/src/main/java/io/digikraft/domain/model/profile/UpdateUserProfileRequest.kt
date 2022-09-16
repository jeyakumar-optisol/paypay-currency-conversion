package io.digikraft.domain.model.profile

data class UpdateUserProfileRequest(
    val bio: String,
    val firstName: String,
    val lastName: String,
    val username: String
)