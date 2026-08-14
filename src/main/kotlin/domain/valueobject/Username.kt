package domain.valueobject

@JvmInline
value class Username private constructor(
    val value: String
) {
    companion object {
        private val USERNAME_REGEX = Regex("^[a-z0-9_]+$")

        fun of(raw: String): Username {
            val normalized = raw
                .trim()
                .removePrefix("@")
                .lowercase()

            require(normalized.length in 5..32) {
                "Username must be between 5 and 32 characters"
            }

            require(USERNAME_REGEX.matches(normalized)) {
                "Username can contain only letters a-z, digits and underscores"
            }

            return Username(normalized)
        }
    }
}