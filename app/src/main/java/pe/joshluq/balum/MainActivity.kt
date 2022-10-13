package pe.joshluq.balum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import pe.joshluq.balum.common.navigation.BalumNavGraph
import pe.joshluq.balum.common.navigation.Destination
import pe.joshluq.balum.ui.theme.BalumTheme

typealias Navigable = ((Destination) -> Unit)?

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            BalumTheme{
                BalumNavGraph()
            }
        }
    }
}

@Composable
fun BalumScreen(isForPreview: Boolean = false, content: @Composable () -> Unit) {
    BalumTheme {
        val background = MaterialTheme.colors.background
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = background,
            content = content
        )
    }
}