package domain.exception

class UsernameAlreadyExistsException : RuntimeException(
    "User with this username already exists"
)