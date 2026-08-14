import application.RegisterUser
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.response.respond
import io.ktor.server.request.receive
import io.ktor.server.routing.post
import presentation.dto.RegisterRequest
import presentation.dto.RegisterResponse

fun Application.configureRouting(
    registerUser: RegisterUser
) {
    routing {
        get("/health") {
            call.respondText("ok")
        }

        get("/hello") {
            call.respondText("Hello, world!")
        }

        post("/auth/register") {
            val request = call.receive<RegisterRequest>()

            val user = registerUser.execute(
                email = request.email,
                username = request.username,
                nickname = request.nickname,
                password = request.password
            )

            call.respond(
                HttpStatusCode.Created,
                RegisterResponse(
                    id = user.id.value.toString(),
                    email = user.email.value,
                    username = user.username.value,
                    nickname = user.nickname
                )
            )
        }
    }
}