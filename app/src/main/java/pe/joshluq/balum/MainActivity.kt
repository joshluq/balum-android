package pe.joshluq.balum

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import pe.joshluq.balum.feature.signin.SignInScreen
import pe.joshluq.balum.feature.welcome.WelcomeScreen
import pe.joshluq.balum.ui.theme.BalumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") {
                    WelcomeScreen(onNavigate = { route ->
                        navController.navigate(route)
                    })
                }
                composable("signin") { SignInScreen() }
            }
        }
    }
}


@Composable
fun Screen(isForPreview: Boolean = false, content: @Composable () -> Unit) {
    BalumTheme(isForPreview) {
        val background = MaterialTheme.colors.primary
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = background,
            content = content
        )
    }
}