package data.database

import data.table.UserTable
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseSchema {
    fun initialze(database: Database) {
        transaction(database) {
            SchemaUtils.create(UserTable)
        }
    }
}