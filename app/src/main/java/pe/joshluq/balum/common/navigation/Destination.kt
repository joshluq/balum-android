package pe.joshluq.balum.common.navigation

import androidx.navigation.NavHostController

private object BalumScreens {
    const val WELCOME_SCREEN = "welcome"
    const val SIGN_IN_SCREEN = "signIn"
}

sealed class Destination(val route: String) {
    object Welcome : Destination(BalumScreens.WELCOME_SCREEN)
    object SignIn : Destination(BalumScreens.SIGN_IN_SCREEN)
}


class NavigationActions(private val navController: NavHostController) {

    fun navigateToSignIn() {
        navController.navigate(Destination.SignIn.route)
    }
}
