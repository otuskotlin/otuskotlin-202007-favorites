package ru.otus.otuskotlin.favorites.mp.validators

interface Validator<T> {
    fun validate(arg: T): ValidationResult
}
