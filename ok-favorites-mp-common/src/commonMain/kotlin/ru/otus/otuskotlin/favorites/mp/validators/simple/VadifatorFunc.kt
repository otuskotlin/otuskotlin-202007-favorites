package ru.otus.otuskotlin.favorites.mp.validators.simple

import ru.otus.otuskotlin.favorites.mp.validators.ValidationResult
import ru.otus.otuskotlin.favorites.mp.validators.Validator

class ValidatorFunc<T>(
    private val validator: (T) -> ValidationResult
) : Validator<T> {
    override fun validate(arg: T): ValidationResult = validator(arg)
}

