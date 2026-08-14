package data.database

import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseFactory {
    fun connect(config: DatabaseConfig): Database {
        val database = Database.connect(
            url = config.jdbcUrl,
            driver = "org.postgresql.Driver",
            user = config.user,
            password = config.password
        )

        transaction(database) {
            exec("SELECT 1")
        }

        return database
    }
}