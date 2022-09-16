package io.digikraft.domain.model.profile

data class ProfileItem(
    val avatarUrl: String,
    val bio: String,
    val birthday: String,
    val createdAt: String,
    val deletedAt: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val uid: String,
    val updatedAt: String,
    val username: String,
    val walletAddress: String
)