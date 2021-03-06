package ru.otus.otuskotlin.favorites.mp.validators.fields

import ru.otus.otuskotlin.favorites.mp.validators.Validator
import ru.otus.otuskotlin.favorites.mp.validators.ValidationResult
import ru.otus.otuskotlin.favorites.mp.validators.simple.ValidatorStringBlank
import ru.otus.otuskotlin.favorites.mp.validators.simple.ValidatorStringRegex

class ValidatorId(
        val field: String = ""
): Validator<String?> {
    private val validatorEmpty = ValidatorStringBlank(field = field)
    private val validatorRegex = ValidatorStringRegex(
            regex = Regex("""[0-9a-zA-Z-]+"""),
            code = "id-validation-symbols",
            group = "validation",
            field = field,
            message = ""
    )
    override fun validate(arg: String?): ValidationResult = ValidationResult(
            *validatorEmpty.validate(arg).errors.toTypedArray(),
            *(arg?.let { validatorRegex.validate(it).errors.toTypedArray() } ?: emptyArray())
    )
}
