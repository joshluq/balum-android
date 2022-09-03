package pe.joshluq.balum.common.navigation

sealed class Destination(val route: String) {
    object Welcome : Destination("welcome")
    object SignIn : Destination("signIn")
}