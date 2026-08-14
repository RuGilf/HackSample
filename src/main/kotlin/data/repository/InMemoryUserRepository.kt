package data.repository

import domain.repository.UserRepository
import domain.model.User
import domain.valueobject.Email
import domain.valueobject.Username

class InMemoryUserRepository : UserRepository {
    private val users = mutableListOf<User>()

    override fun findByEmail(email: Email): User? {
        return users.find { user -> user.email == email }
    }

    override fun save(user: User): User {
        users.add(user)
        return user
    }

    override fun findByUsername(username: Username): User? {
        return users.find { user ->
            user.username == username
        }
    }
}