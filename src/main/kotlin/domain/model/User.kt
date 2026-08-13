package domain.model

import domain.valueobject.Email
import domain.valueobject.UserId


data class User(
    val id: UserId,
    val email: Email,
    val username: String,
    val passwordHash: String
)