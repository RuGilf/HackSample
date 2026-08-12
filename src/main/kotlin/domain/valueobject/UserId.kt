package domain.valueobject

import java.util.UUID

@JvmInline
value class UserId(
    val value: UUID
)