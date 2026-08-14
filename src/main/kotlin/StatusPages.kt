import domain.exception.EmailAlreadyExistsException
import domain.exception.UsernameAlreadyExistsException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.application.install
import presentation.dto.ErrorResponse

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<EmailAlreadyExistsException> { call, exception ->
            call.respond(
                HttpStatusCode.Conflict,
                ErrorResponse(
                    message = exception.message
                        ?: "User with this email already exists"
                )
            )
        }

        exception<IllegalArgumentException> { call, exception ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse(
                    message = exception.message
                        ?: "Invalid request"
                )
            )
        }

        exception<UsernameAlreadyExistsException> { call, exception ->
            call.respond(
                HttpStatusCode.Conflict,
                ErrorResponse(
                    message = exception.message
                        ?: "User with this username already exists"
                )
            )
        }
    }
}