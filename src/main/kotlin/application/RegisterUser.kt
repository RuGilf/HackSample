package application

import application.security.PasswordHasher
import domain.exception.EmailAlreadyExistsException
import domain.repository.UserRepository
import domain.model.User
import domain.valueobject.Email
import java.util.UUID
import domain.valueobject.UserId

class RegisterUser(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
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

        val passwordHash = passwordHasher.hash(password)

        val user = User(
            id = UserId(UUID.randomUUID()),
            email = userEmail,
            username = username,
            passwordHash = passwordHash
        )

        return userRepository.save(user)
    }
}