package application

import domain.exception.EmailAlreadyExistsException
import domain.repository.UserRepository
import domain.model.User
import domain.valueobject.Email
import java.util.UUID
import domain.valueobject.UserId

class RegisterUser(
    private val userRepository: UserRepository
) {
    fun execute(
        email: String,
        username: String,
        password: String
    ) : User {
        val userEmail = Email(email)

        val existingUser = userRepository.findByEmail(userEmail)

        if (existingUser != null) {
            throw EmailAlreadyExistsException()
        }

        val user = User(
            id = UserId(UUID.randomUUID()),
            email = userEmail,
            username = username,
            password = password
        )

        return userRepository.save(user)
    }
}