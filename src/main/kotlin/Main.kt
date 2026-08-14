import application.RegisterUser
import application.security.PasswordHasher
import data.database.DatabaseConfig
import data.database.DatabaseFactory
import data.database.DatabaseSchema
import data.repository.PostgresUserRepository
import data.security.BCryptPasswordHasher
import domain.repository.UserRepository
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val databaseConfig = DatabaseConfig.fromEnvironment()
    val database = DatabaseFactory.connect(
        databaseConfig
    )

    DatabaseSchema.initialize(database)

    val userRepository: UserRepository = PostgresUserRepository(database)
    val passwordHasher: PasswordHasher = BCryptPasswordHasher()

    val registerUser = RegisterUser(
        userRepository = userRepository,
        passwordHasher = passwordHasher
    )

    embeddedServer(
        factory = Netty,
        host = "0.0.0.0",
        port = 8080
    ) {
        configureRouting(registerUser)
        configureSerialization()
        configureStatusPages()
    }.start(wait = true)
}