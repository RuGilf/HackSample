package presentation.dto

import kotlinx.serialization.Serializable

@Serializable
class RegisterResponse(
    val id: String,
    val email: String,
    val username: String
) {
}
