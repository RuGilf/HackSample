import application.RegisterUser
import application.security.PasswordHasher
import data.database.DatabaseConfig
import data.database.DatabaseFactory
import data.repository.InMemoryUserRepository
import data.security.BCryptPasswordHasher
import domain.repository.UserRepository
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val databaseConfig = DatabaseConfig.fromEnvironment()

    DatabaseFactory.connect(databaseConfig)

    val userRepository: UserRepository = InMemoryUserRepository()
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