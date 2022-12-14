package pe.joshluq.balum.feature.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.joshluq.balum.R
import pe.joshluq.balum.common.resource.ResourceProvider
import pe.joshluq.balum.common.resource.provideErrorMessage
import pe.joshluq.balum.domain.model.Credential
import pe.joshluq.balum.domain.model.User
import pe.joshluq.balum.domain.usecase.SignInUseCase
import pe.joshluq.balum.domain.usecase.UseCase
import pe.joshluq.balum.domain.validation.*
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SignInViewModel @Inject constructor(
    @Named("signInUseCase") private val signInUseCase: UseCase<SignInUseCase.Params, User>,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    var state by mutableStateOf<SignInState>(InitialState)
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun signIn() = viewModelScope.launch {
        state = LoadingState
        val credential = Credential(email, password)
        val params = SignInUseCase.Params(credential)
        signInUseCase(params)
            .onSuccess(::onSingInSuccess)
            .onFailure(::onSignInFailure)
    }

    private fun onSingInSuccess(user: User) {
        state = SignInSuccessfulState
    }

    private fun onSignInFailure(throwable: Throwable) = throwable
        .onInvalidFormatError(::onFieldFormatError)
        .onEmptyFieldError(::onFieldEmptyError)
        .onDefaultError(::showMessage)

    private fun showMessage(throwable: Throwable) {
        state = ErrorState(provideErrorMessage(resourceProvider, throwable))
    }

    private fun onFieldEmptyError(field: Field) {
        if (field == Field.EMAIL) {
            state = EmailErrorState(
                resourceProvider.getString(R.string.signin_email_field_required_message)
            )
            return
        }
        if (field == Field.PASSWORD) {
            state = PasswordErrorState(
                resourceProvider.getString(R.string.signin_password_field_required_message)
            )
            return
        }
    }

    private fun onFieldFormatError(field: Field) {
        if (field == Field.EMAIL) {
            state = EmailErrorState(
                resourceProvider.getString(R.string.signin_email_field_invalid_message)
            )
            return
        }
        if (field == Field.PASSWORD) {
            state = PasswordErrorState(
                resourceProvider.getString(R.string.signin_password_field_invalid_message)
            )
            return
        }
    }

    fun clearError() {
        state = InitialState
    }
}