package domain.valueobject

import kotlin.uuid.Uuid

@JvmInline
value class UserId(
    val value: Uuid
) {
    companion object {
        fun random(): UserId {
            return UserId(Uuid.random())
        }
    }
}