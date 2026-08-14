package data.database

import data.database.table.UsersTable
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseSchema {
    fun initialize(database: Database) {
        transaction(database) {
            SchemaUtils.create(UsersTable)
        }
    }
}