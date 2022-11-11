package pe.joshluq.balum.feature.credentialrecovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.joshluq.balum.R
import pe.joshluq.balum.common.navigation.CredentialRecoveryNavigator
import pe.joshluq.balum.ui.theme.BalumTheme
import pe.joshluq.balum.ui.theme.LightBlue1
import pe.joshluq.balum.ui.widget.PrimaryButton

@Composable
fun CredentialRecoverySuccessScreen(
    modifier: Modifier = Modifier,
    navigator: CredentialRecoveryNavigator = CredentialRecoveryNavigator
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        backgroundColor = LightBlue1,
        topBar = {
            TopAppBar(
                backgroundColor = LightBlue1, contentColor = Color.Black
            ) {
                RecoveryAppBar(navigator)
            }
        }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            Body()
            ButtonContainer {
                navigator.navigateToBack()
            }
        }
    }
}

@Preview
@Composable
fun CredentialRecoverySuccessScreenPreview() {
    BalumTheme {
        CredentialRecoverySuccessScreen()
    }
}

@Composable
private fun RecoveryAppBar(navigator: CredentialRecoveryNavigator = CredentialRecoveryNavigator) {
    Box(contentAlignment = Alignment.CenterStart) {
        Text(
            text = stringResource(R.string.credentialrecovery_title),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        IconButton(onClick = {
            navigator.navigateToBack()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun Body(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(32.dp),
            text = stringResource(R.string.credentialrecovery_subtitle),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.img_send_email),
            contentDescription = null,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
private fun ButtonContainer(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        PrimaryButton(
            text = stringResource(R.string.credentialrecovery_back_button),
            onClick = onBackButtonClick
        )
        Spacer(Modifier.height(32.dp))
    }
}
