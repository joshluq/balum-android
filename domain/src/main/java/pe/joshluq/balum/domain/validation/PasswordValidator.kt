package pe.joshluq.balum.domain.validation

inline fun String.ifPasswordError(
    field: Field = Field.PASSWORD,
    defaultValue: (ValidationResult) -> Unit
) = when {
    isNullOrBlank() -> defaultValue(ValidationResult.EmptyField(field))
    else -> Unit
}
