package pe.joshluq.balum.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.joshluq.balum.feature.signin.SignInScreen
import pe.joshluq.balum.feature.signup.SignUpScreen
import pe.joshluq.balum.feature.welcome.WelcomeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Destination = Destination.Welcome,
    navAction: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {

    val welcomeNavigator = WelcomeNavigator.build(navAction)
    val signInNavigator = SignInNavigator.build(navAction)
    val signUpNavigator = SignUpNavigator.build(navAction)

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
    ) {
        composable(Destination.Welcome.route) {
            WelcomeScreen(
                modifier = modifier,
                navigator = welcomeNavigator
            )
        }
        composable(Destination.SignIn.route) {
            SignInScreen(
                modifier = modifier,
                navigator = signInNavigator
            )
        }
        composable(Destination.SignUp.route) {
            SignUpScreen(
                modifier = modifier,
                navigator = signUpNavigator
            )
        }
    }
}

