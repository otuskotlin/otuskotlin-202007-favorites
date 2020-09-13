import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@JsModule("mathjs")
@JsNonModule
@JsName("mathjs")
external val mathjs: dynamic

@JsModule("is-sorted")
@JsNonModule
external fun <T> sorted(a: Array<T>): Boolean

internal class JsTest {

    @Test
    @JsName("createTest")
    fun `create test`() {
        val result: dynamic = js("""
                var module = require('otuskotlin-202007-favorites-ok-favorites-mp-common');
                new module.ru.otus.otuskotlin.favorites.mp.validators.ValidationResult();
            """)
        assertEquals("ValidationResult", result::class.simpleName)
    }

    @Test
    @JsName("externalSqrtTest")
    fun `external test`() {
        val res = mathjs.sqrt(16)
        assertEquals(4, res)
    }

    @Test
    @JsName("externalSqrt1Test")
    fun `external test 1`() {
        val res = mathjs.sqrt(81)
        assertEquals(9, res)
    }

    @Test
    @JsName("externalIsSortedTest")
    fun `is-sorted test`() {
        assertFalse { sorted(arrayOf(3,1,7)) }
        assertTrue { sorted(arrayOf(1,2,3)) }
    }

}
