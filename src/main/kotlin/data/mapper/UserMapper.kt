package data.mapper

import data.database.table.UsersTable
import domain.model.User
import domain.valueobject.Email
import domain.valueobject.UserId
import domain.valueobject.Username
import org.jetbrains.exposed.v1.core.ResultRow

fun ResultRow.toUser() = with(UsersTable) {
    User(
        id = UserId(this@toUser[id]),
        email = Email(this@toUser[email]),
        username = Username.of(this@toUser[username]),
        nickname = this@toUser[nickname],
        passwordHash = this@toUser[passwordHash]
    )
}