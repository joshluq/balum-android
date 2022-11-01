package pe.joshluq.balum.feature.credentialrecovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.joshluq.balum.R
import pe.joshluq.balum.common.navigation.CredentialRecoveryNavigator
import pe.joshluq.balum.ui.theme.BalumTheme
import pe.joshluq.balum.ui.theme.LightBlue1
import pe.joshluq.balum.ui.widget.*

@Composable
fun CredentialRecoveryScreen(
    modifier: Modifier = Modifier,
    viewModel: CredentialRecoveryViewModel = hiltViewModel(),
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
            Header(Modifier.weight(1f))
            Form(Modifier.weight(3f), viewModel)
        }
    }
    if (viewModel.state is LoadingState) {
        LoadingDialog {}
    }
    val message = (viewModel.state as? ErrorState)?.message.orEmpty()
    if (message.isNotBlank()) {
        SimpleDialog(description = message) {
            viewModel.clearError()
        }
    }
}

@Preview
@Composable
fun CredentialRecoveryScreenPreview() {
    BalumTheme {
        CredentialRecoveryScreen()
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
private fun Header(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.padding(32.dp),
            text = stringResource(R.string.credentialrecovery_subtitle),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Form(
    modifier: Modifier = Modifier,
    viewModel: CredentialRecoveryViewModel
) {
    val focusManager = LocalFocusManager.current
    val emailMessage = (viewModel.state as? EmailErrorState)?.message.orEmpty()
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        EmailTextField(
            value = viewModel.email,
            onValueChange = { newValue ->
                viewModel.clearError()
                viewModel.email = newValue
            },
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            errorMessage = ErrorMessage(emailMessage)
        )
        ButtonContainer(
            onResetButtonClick = {
                viewModel.recoverCredential()
            }
        )
    }
}


@Composable
private fun ButtonContainer(
    modifier: Modifier = Modifier,
    onResetButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        PrimaryButton(
            text = stringResource(R.string.credentialrecovery_button),
            onClick = onResetButtonClick
        )
        Spacer(Modifier.height(32.dp))
    }
}
