package pe.joshluq.balum.feature.signin

sealed interface SignInState

object InitialState : SignInState

object LoadingState : SignInState

object SignInSuccessfulState : SignInState

class ErrorState(val message: String) : SignInState

class EmailErrorState(val message: String) : SignInState

class PasswordErrorState(val message: String) : SignInState