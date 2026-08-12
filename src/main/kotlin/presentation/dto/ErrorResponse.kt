package presentation.dto

import kotlinx.serialization.Serializable

@Serializable
class ErrorResponse(
    val message: String
) {
}
