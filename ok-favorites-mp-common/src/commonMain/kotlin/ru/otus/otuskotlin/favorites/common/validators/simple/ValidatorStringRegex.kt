package ru.otus.otuskotlin.favorites.common.validators.simple

import ru.otus.otuskotlin.favorites.common.validators.HandleError
import ru.otus.otuskotlin.favorites.common.validators.ValidationResult
import ru.otus.otuskotlin.favorites.common.validators.Validator

class ValidatorStringRegex(
    private val regex: Regex,
    private val code: String = "wrong-symbols",
    private val field: String = "",
    private val group: String = "validation",
    private val message: String = "Field contains inappropriate characters",
    private val level: HandleError.Level = HandleError.Level.ERROR
) : Validator<String> {
    override fun validate(arg: String): ValidationResult = ValidationResult(
        regex.replace(arg, "").let {
            if (it.isNotEmpty())
                HandleError(
                    code = code,
                    field = field,
                    group = group,
                    message = "$message: '$it'",
                    level = level
                )
            else null
        }
    )
}
