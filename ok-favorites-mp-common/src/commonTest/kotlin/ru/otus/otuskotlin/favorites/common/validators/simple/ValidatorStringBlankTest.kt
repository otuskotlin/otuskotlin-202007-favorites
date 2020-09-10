package ru.otus.otuskotlin.favorites.common.validators.simple

import ru.otus.otuskotlin.favorites.common.validators.HandleError
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ValidatorStringBlankTest {
    @Test
    fun nonEmpty() {
        val validation = ValidatorStringBlank().validate(" th ")
        assertEquals(HandleError.Level.NONE, validation.level)
    }

    @Test
    fun nullValue() {
        val validation = ValidatorStringBlank().validate(null)
        assertEquals(HandleError.Level.ERROR, validation.level)
    }

    @Test
    fun emptyValue() {
        val validation = ValidatorStringBlank().validate("   ")
        assertEquals(HandleError.Level.ERROR, validation.level)
    }
}

