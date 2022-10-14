package pe.joshluq.balum.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.joshluq.balum.ui.theme.BalumTheme

@Composable
fun PasswordTextField(
    value: String,
    label: String = "Password",
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMessage: ErrorMessage = ErrorMessage(),
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(horizontal = 36.dp),
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = passwordKeyboardOption(imeAction),
            keyboardActions = keyboardActions,
            visualTransformation = passwordVisualState(isPasswordVisible),
            trailingIcon = {
                IconToggleButton(
                    checked = isPasswordVisible,
                    onCheckedChange = { isPasswordVisible = it }) {
                    Icon(imageVector = getToggleIcon(isPasswordVisible), contentDescription = null)
                }
            },
            singleLine = true,
            isError = errorMessage.isError,
        )
        if (errorMessage.isError) {
            Text(
                text = errorMessage.value,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    BalumTheme {
        Column {
            PasswordTextField("") {}
            PasswordTextField("", errorMessage = ErrorMessage("Error")) {}
        }
    }
}

private fun passwordKeyboardOption(imeAction: ImeAction = ImeAction.Default) = KeyboardOptions(
    autoCorrect = false,
    keyboardType = KeyboardType.Password,
    imeAction = imeAction
)

private fun passwordVisualState(isPasswordVisible: Boolean = false) =
    if (isPasswordVisible) VisualTransformation.None
    else PasswordVisualTransformation()

private fun getToggleIcon(isPasswordVisible: Boolean = false) =
    if (isPasswordVisible) Icons.Default.VisibilityOff
    else Icons.Default.Visibility


class ErrorMessage(
    val value: String = ""
) {
    val isError: Boolean
        get() = value.isNotBlank()
}

@Composable
fun EmailTextField(
    value: String,
    label: String = "Email",
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMessage: ErrorMessage = ErrorMessage(),
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 36.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = label)
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            isError = errorMessage.isError
        )

        if (errorMessage.isError) {
            Text(
                text = errorMessage.value,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    BalumTheme {
        Column {
            EmailTextField("") {}
            EmailTextField("", errorMessage = ErrorMessage("Error")) {}
        }
    }
}
