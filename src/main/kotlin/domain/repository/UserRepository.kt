package domain.repository

import domain.valueobject.Email
import domain.model.User
import domain.valueobject.Username

interface UserRepository {
    fun findByEmail(email: Email): User?
    fun findByUsername(username: Username): User?

    fun save(user: User): User
}