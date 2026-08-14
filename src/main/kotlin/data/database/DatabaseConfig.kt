package data.database

class DatabaseConfig(
    val host: String,
    val port: Int,
    val name: String,
    val user: String,
    val password: String
) {
    val jdbcUrl: String
        get() = "jdbc:postgresql://$host:$port/$name"

    companion object {
        fun fromEnvironment(): DatabaseConfig {
            return DatabaseConfig(
                host = requiredEnv("DATABASE_HOST"),
                port = requiredEnv("DATABASE_PORT").toInt(),
                name = requiredEnv("POSTGRES_DB"),
                user = requiredEnv("POSTGRES_USER"),
                password = requiredEnv("POSTGRES_PASSWORD")
            )
        }

        private fun requiredEnv(name: String): String {
            return System.getenv(name)
                ?: error("Environment variable $name is not set")
        }
    }
}