package data.database.table

import org.jetbrains.exposed.v1.core.Table

object UsersTable : Table("users") {
    val id = uuid("id")

    val email = varchar(
        name = "email",
        length = 255
    ).uniqueIndex()

    val username = varchar(
        name = "username",
        length = 32
    ).uniqueIndex()

    val nickname = varchar(
        name = "nickname",
        length = 64
    )

    val passwordHash = varchar(
        name = "password_hash",
        length = 255
    )

    override val primaryKey = PrimaryKey(id)
}