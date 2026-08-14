package data.table

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.core.java.javaUUID

object UserTable : Table("users") {
    val id = javaUUID("id")

    val email = varchar(
        name = "email",
        length = 255
    ).uniqueIndex()

    val username = varchar(
        name = "username",
        length = 50
    )

    val passwordHash = varchar(
        name = "password_hash",
        length = 255
    ).uniqueIndex()

    override val primaryKey = PrimaryKey(id)
}