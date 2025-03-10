package space.reul.cleanarchitectureexample.domain.model

import kotlin.test.Test
import kotlin.test.assertEquals

class UrlsTest {
    @Test
    fun `test Urls is a type alias to ArrayList`() {
        val expected = arrayListOf<String>()
        val actual = Cat()

        assertEquals(expected, actual)
        assertEquals(expected::class, actual::class)
    }
}
