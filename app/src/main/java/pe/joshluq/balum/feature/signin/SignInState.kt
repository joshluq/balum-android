package pe.joshluq.balum.feature.signin

import pe.joshluq.balum.common.state.LoadingState
import pe.joshluq.balum.common.uimodel.UiError

data class SignInState(
    val onSignInSuccessful: Boolean = false,
    val onSingInButtonClicked: Boolean = false,
    val onUsernameError: String = "",
    val onPasswordError: String = "",
    override val isLoading: Boolean = false,
    override val showError: UiError? = null,
    override val onDismissError: Boolean = false
) : LoadingState