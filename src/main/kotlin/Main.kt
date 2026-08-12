import application.RegisterUser
import data.repository.InMemoryUserRepository
import domain.repository.UserRepository
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val userRepository: UserRepository = InMemoryUserRepository()

    val registerUser = RegisterUser(
        userRepository = userRepository
    )

    embeddedServer(
        factory = Netty,
        port = 8080
    ) {
        configureRouting(registerUser)
        configureSerialization()
        configureStatusPages()
    }.start(wait = true)
}