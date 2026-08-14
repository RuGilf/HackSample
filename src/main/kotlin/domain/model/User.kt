package domain.model

import domain.valueobject.Email
import domain.valueobject.UserId
import domain.valueobject.Username

data class User(
    val id: UserId,
    val email: Email,
    val username: Username,
    val nickname: String,
    val passwordHash: String
)