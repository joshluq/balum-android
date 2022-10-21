package pe.joshluq.balum.feature.signup

sealed class SignUpState{

    object InitialState : SignUpState()

    object LoadingState : SignUpState()

    object CreateUserSuccessfulState : SignUpState()

    class ErrorState(val message: String) : SignUpState()

    class NameErrorState(val message: String) : SignUpState()

    class LastNameErrorState(val message: String) : SignUpState()

    class EmailErrorState(val message: String) : SignUpState()

    class PasswordErrorState(val message: String) : SignUpState()
}
