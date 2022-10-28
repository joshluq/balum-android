package pe.joshluq.balum.common.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions

private object Screens {
    const val WELCOME_SCREEN = "welcome"
    const val SIGN_IN_SCREEN = "signIn"
    const val SIGN_UP_SCREEN = "signUp"
    const val FORGOT_PASSWORD_SCREEN = "forgotPassword"
}

sealed class Destination(val route: String) {
    object Welcome : Destination(Screens.WELCOME_SCREEN)
    object SignIn : Destination(Screens.SIGN_IN_SCREEN)
    object SignUp : Destination(Screens.SIGN_UP_SCREEN)
    object ForgotPassword : Destination(Screens.FORGOT_PASSWORD_SCREEN)
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToBack(){
        navController.popBackStack()
    }

    fun navigateToSignIn(navOptions: NavOptions? = null) {
        navController.navigate(Destination.SignIn.route, navOptions)
    }

    fun navigateToSignUp(navOptions: NavOptions? = null) {
        navController.navigate(Destination.SignUp.route, navOptions)
    }

    fun navigateToForgotPassword(navOptions: NavOptions? = null) {
        navController.navigate(Destination.ForgotPassword.route, navOptions)
    }
}
