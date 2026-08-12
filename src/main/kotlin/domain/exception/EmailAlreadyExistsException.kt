package domain.exception

class EmailAlreadyExistsException : RuntimeException(
    "User with this email already exists"
)