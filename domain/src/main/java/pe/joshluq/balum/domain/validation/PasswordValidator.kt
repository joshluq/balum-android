package pe.joshluq.balum.domain.validation

internal const val MAX_PASSWORD_LENGTH = 4

internal inline fun String.ifPasswordError(
    field: Field = Field.PASSWORD,
    defaultValue: (ValidationResult) -> Unit
) = when {
    isNullOrBlank() -> defaultValue(ValidationResult.EmptyField(field))
    length < MAX_PASSWORD_LENGTH -> defaultValue(ValidationResult.InvalidFormat(field))
    else -> Unit
}
