package pe.joshluq.balum.common.uimodel

data class UiError(
    val type: UiErrorType = UiErrorType.DEFAULT,
    val message: String = ""
)
