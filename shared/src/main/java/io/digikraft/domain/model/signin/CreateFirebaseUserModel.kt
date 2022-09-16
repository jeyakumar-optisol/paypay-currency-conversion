package io.digikraft.domain.model.signin

data class CreateFirebaseUserModel(
    val username:String,
    val email: String,
    val password: String
)