package pe.joshluq.balum.common.state

import pe.joshluq.balum.common.uimodel.UiError

interface LoadingState {
    val isLoading: Boolean
    val showError: UiError?
    val onDismissError: Boolean
}
