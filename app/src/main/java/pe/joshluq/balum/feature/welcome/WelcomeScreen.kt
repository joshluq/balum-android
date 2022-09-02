package pe.joshluq.balum.feature.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import pe.joshluq.balum.R
import pe.joshluq.balum.Screen

@Composable
fun WelcomeScreen(isForPreview: Boolean = false) {
    Screen(isForPreview) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_balum_logo),
                contentDescription = null, // decorative element
            )
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun WelcomePreview() {
    WelcomeScreen(true)
}