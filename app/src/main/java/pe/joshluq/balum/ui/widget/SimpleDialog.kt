package pe.joshluq.balum.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pe.joshluq.balum.ui.theme.BalumTheme

@Composable
fun SimpleDialog(
    title: String = "",
    description: String = "",
    buttonText: String = "Entendido",
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier,
            elevation = 8.dp
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                if (title.isNotBlank()) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .padding(16.dp), textAlign = TextAlign.Center
                    )
                }

                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(8.dp), textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    PrimaryButton(
                        text = buttonText,
                        onClick = onDismiss
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleDialogPreview() {
    BalumTheme {
        SimpleDialog(description = "Description") {

        }
    }
}