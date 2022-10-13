package pe.joshluq.balum.feature.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pe.joshluq.balum.R
import pe.joshluq.balum.ui.theme.BalumTheme
import pe.joshluq.balum.ui.theme.LightBlue1

@Composable
fun SignInScreen(modifier: Modifier = Modifier, viewModel: SignInViewModel = hiltViewModel()) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlue1),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(Modifier.weight(2f))
            Form(Modifier.weight(3f), viewModel)
        }
        if (viewModel.state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    BalumTheme {
        SignInScreen()
    }
}


@Composable
private fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Welcome back!",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 32.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.img_signin),
            contentDescription = null,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

@Composable
private fun Form(modifier: Modifier = Modifier, viewModel: SignInViewModel = hiltViewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UsernameField(
            value = username,
            onValueChange = { username = it },
            viewModel.state.onUsernameError
        )
        PasswordField(value = password, onValueChange = { password = it })
        ButtonContainer(onClick = {
            viewModel.signIn(username, password)
        })
    }
}


@Composable
private fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit,
    textError: String = ""
) {
    val hasError = textError.isNotBlank()
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Username")
        },
        isError = hasError
    )
    if (hasError) {
        Text(
            text = textError, color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Password")
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = isPasswordVisible,
                onCheckedChange = { isPasswordVisible = it }) {
                val icon = if (isPasswordVisible) Icons.Default.VisibilityOff
                else Icons.Default.Visibility
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}

@Composable
private fun ButtonContainer(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = onClick) {
            Text(text = stringResource(R.string.signin_signin_button))
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(R.string.signin_signup_button))
        }
    }
}
