package domain.valueobject

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UsernameTest {
    @Test
    fun `username is normalized`() {
        val username = Username.of(" @DEnChiK1395  ")

        assertEquals(
            expected = "denchik1395",
            actual = username.value
        )
    }

    @Test
    fun `username can contain digits and underscores`() {
        val username = Username.of("den_chick2599")

        assertEquals(
            expected = "den_chick2599",
            actual = username.value
        )
    }

    @Test
    fun `username shorter that 5 characters is rejected`() {
        assertFailsWith<IllegalArgumentException> {
            Username.of("abc")
        }
    }

    @Test
    fun `username longer than 32 characters is rejected`() {
        assertFailsWith<IllegalArgumentException> {
            Username.of("a".repeat(33))
        }
    }

    @Test
    fun `username with invalid characters is rejecred`() {
        assertFailsWith<IllegalArgumentException> {
            Username.of("bobr-dobr")
        }
    }
}