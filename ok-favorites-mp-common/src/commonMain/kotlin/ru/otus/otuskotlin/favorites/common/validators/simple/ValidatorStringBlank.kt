package ru.otus.otuskotlin.favorites.common.validators.simple

import ru.otus.otuskotlin.favorites.common.validators.HandleError
import ru.otus.otuskotlin.favorites.common.validators.ValidationResult
import ru.otus.otuskotlin.favorites.common.validators.Validator

class ValidatorStringBlank(
    val field: String = ""
) : Validator<String?> {
    override fun validate(arg: String?): ValidationResult = ValidationResult(
        if (arg == null || arg.isBlank())
            HandleError(
                message = "Field ${field.takeIf { it.isNotBlank() } ?: ""} cannot be blank",
                level = HandleError.Level.ERROR
            )
        else null
    )
}
