package data.repository

import data.database.DatabaseConfig
import data.database.DatabaseFactory
import data.database.DatabaseSchema
import data.database.table.UsersTable
import domain.model.User
import domain.valueobject.Email
import domain.valueobject.UserId
import domain.valueobject.Username
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.deleteAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.postgresql.PostgreSQLContainer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostgresUserRepositoryTest {

    companion object {
        @Container
        @JvmField
        val postgres = PostgreSQLContainer("postgres:18")
            .withDatabaseName("hacksample_test")
            .withUsername("test")
            .withPassword("test")
    }

    private lateinit var database: Database
    private lateinit var repository: PostgresUserRepository

    @BeforeAll
    fun connectToDatabase() {
        val config = DatabaseConfig(
            host = postgres.host,
            port = postgres.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
            name = postgres.databaseName,
            user = postgres.username,
            password = postgres.password
        )

        database = DatabaseFactory.connect(config)

        DatabaseSchema.initialize(database)

        repository = PostgresUserRepository(database)
    }

    @BeforeEach
    fun clearDatabase() {
        transaction(database) {
            UsersTable.deleteAll()
        }
    }

    @Test
    fun `saved user can be found by email`() {
        val user = createUser()

        repository.save(user)

        val foundUser = repository.findByEmail(user.email)

        assertEquals(user, foundUser)
    }

    @Test
    fun `saved user can be found by username`() {
        val user = createUser()

        repository.save(user)

        val foundUser = repository.findByUsername(user.username)

        assertEquals(user, foundUser)
    }

    @Test
    fun `unknown email returns null`() {
        val foundUser = repository.findByEmail(
            Email("unknown@example.com")
        )

        assertNull(foundUser)
    }

    private fun createUser(
        email: String = "bob@example.com",
        username: String = "bob_123"
    ): User {
        return User(
            id = UserId.random(),
            email = Email(email),
            username = Username.of(username),
            nickname = "Bob",
            passwordHash = "hashed-password"
        )
    }
}