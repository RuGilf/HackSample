package domain.valueobject

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EmailTest {
    @Test
    fun `valid email is created`() {
        val email = Email("antihype@hype.train")

        assertEquals(
            expected = "antihype@hype.train",
            actual = email.value
        )
    }

    @Test
    fun `email without at sign is rejected`() {
        assertFailsWith<IllegalArgumentException> {
            Email("dot.dot.dot")
        }
    }
}