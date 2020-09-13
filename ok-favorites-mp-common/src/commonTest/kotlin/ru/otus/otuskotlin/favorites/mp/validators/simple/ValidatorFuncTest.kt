package ru.otus.otuskotlin.common.validators

import ru.otus.otuskotlin.favorites.mp.validators.HandleError
import ru.otus.otuskotlin.favorites.mp.validators.ValidationResult
import ru.otus.otuskotlin.favorites.mp.validators.simple.ValidatorFunc
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ValidatorFuncTest {

    @Test
    fun funcValidationTest() {
        val validator = ValidatorFunc<String> {
            ValidationResult(
                    if (!it.contains("ok")) HandleError(
                            message = noOkMessage,
                            level = HandleError.Level.ERROR
                    ) else null
            )
        }

        assertEquals(noOkMessage, validator.validate("error").errors.first().message)
    }

    companion object {
        const val noOkMessage = "String does not contain 'ok' symbols"
    }
}
