package data.repository

import data.database.table.UsersTable
import data.mapper.toUser
import domain.model.User
import domain.repository.UserRepository
import domain.valueobject.Email
import domain.valueobject.Username
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class PostgresUserRepository(
    private val database: Database
) : UserRepository {

    override fun findByEmail(email: Email): User? {
        return transaction(database) {
            UsersTable
                .selectAll()
                .where { UsersTable.email eq email.value }
                .singleOrNull()
                ?.toUser()
        }
    }

    override fun findByUsername(username: Username): User? {
        return transaction(database) {
            UsersTable
                .selectAll()
                .where { UsersTable.username eq username.value }
                .singleOrNull()
                ?.toUser()
        }
    }

    override fun save(user: User): User {
        transaction(database) {
            UsersTable.insert {
                it[id] = user.id.value
                it[email] = user.email.value
                it[username] = user.username.value
                it[nickname] = user.nickname
                it[passwordHash] = user.passwordHash
            }
        }

        return user
    }
}