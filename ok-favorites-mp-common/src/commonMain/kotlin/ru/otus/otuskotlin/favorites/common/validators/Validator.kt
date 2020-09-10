package ru.otus.otuskotlin.favorites.common.validators

interface Validator<T> {
    fun validate(arg: T): ValidationResult
}
