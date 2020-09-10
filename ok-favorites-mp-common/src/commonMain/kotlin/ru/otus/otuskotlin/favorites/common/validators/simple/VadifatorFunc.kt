package ru.otus.otuskotlin.favorites.common.validators.simple

import ru.otus.otuskotlin.favorites.common.validators.ValidationResult
import ru.otus.otuskotlin.favorites.common.validators.Validator

class ValidatorFunc<T>(
    private val validator: (T) -> ValidationResult
) : Validator<T> {
    override fun validate(arg: T): ValidationResult = validator(arg)
}

