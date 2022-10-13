package pe.joshluq.balum.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.joshluq.balum.ui.theme.BalumTheme

enum class ButtonType {
    PRIMARY,
    PRIMARY_WHITE,
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    type: ButtonType = ButtonType.PRIMARY
) {

    val buttonColors = when (type) {
        ButtonType.PRIMARY -> ButtonDefaults.buttonColors(contentColor = MaterialTheme.colors.primary)
        ButtonType.PRIMARY_WHITE -> ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White
        )
    }

    val textColors = when (type) {
        ButtonType.PRIMARY -> Color.White
        ButtonType.PRIMARY_WHITE -> MaterialTheme.colors.primary
    }

    val border = when (type) {
        ButtonType.PRIMARY_WHITE -> BorderStroke(1.dp, MaterialTheme.colors.primary)
        else -> null
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = buttonColors,
        border = border,
        enabled = enabled,
    ) {
        Text(text = text, modifier = modifier.padding(horizontal = 24.dp), color = textColors)
    }
}


@Preview
@Composable
fun PrimaryButtonPreview() {
    BalumTheme {
        PrimaryButton(text = "Primary Button", onClick = {})
    }
}
