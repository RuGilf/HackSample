package application

import application.security.PasswordHasher
import domain.exception.EmailAlreadyExistsException
import domain.exception.UsernameAlreadyExistsException
import domain.repository.UserRepository
import domain.model.User
import domain.valueobject.Email
import java.util.UUID
import domain.valueobject.UserId
import domain.valueobject.Username

class RegisterUser(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
) {
    fun execute(
        email: String,
        username: String,
        nickname: String,
        password: String
    ) : User {
        val userEmail = Email(email)
        val userUsername = Username.of(username)

        if (userRepository.findByEmail(userEmail) != null) {
            throw EmailAlreadyExistsException()
        }

        if (userRepository.findByUsername(userUsername) != null) {
            throw UsernameAlreadyExistsException()
        }

        val passwordHash = passwordHasher.hash(password)

        val user = User(
            id = UserId.random(),
            email = userEmail,
            nickname = nickname,
            username = userUsername,
            passwordHash = passwordHash
        )

        return userRepository.save(user)
    }
}