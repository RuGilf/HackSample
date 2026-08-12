package presentation.dto

import kotlinx.serialization.Serializable

@Serializable
class RegisterRequest(
    val email: String,
    val username: String,
    val password: String
) {
}
