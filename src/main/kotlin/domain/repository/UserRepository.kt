package domain.repository

import domain.valueobject.Email
import domain.model.User

interface UserRepository {
    fun findByEmail(email: Email): User?

    fun save(user: User): User
}