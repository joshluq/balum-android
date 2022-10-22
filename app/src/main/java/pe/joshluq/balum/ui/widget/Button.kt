package pe.joshluq.balum.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.joshluq.balum.ui.theme.BalumTheme

enum class ButtonType {
    PRIMARY,
    SECONDARY,
    WHITE,
}

enum class ButtonBody {
    DEFAULT,
    FIT,
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    type: ButtonType = ButtonType.PRIMARY,
    body: ButtonBody = ButtonBody.DEFAULT,
) {

    val buttonColors = when (type) {
        ButtonType.PRIMARY -> ButtonDefaults.buttonColors()
        ButtonType.SECONDARY -> ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White
        )
        ButtonType.WHITE -> ButtonDefaults.buttonColors(backgroundColor = Color.White)
    }

    val textColors = when (type) {
        ButtonType.PRIMARY -> Color.White
        ButtonType.SECONDARY -> MaterialTheme.colors.primary
        ButtonType.WHITE -> MaterialTheme.colors.primary
    }

    val elevation = when (type) {
        ButtonType.WHITE -> ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
        )
        else -> ButtonDefaults.elevation()
    }

    val border = when (type) {
        ButtonType.SECONDARY -> BorderStroke(1.dp, MaterialTheme.colors.primary)
        else -> null
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = buttonColors,
        border = border,
        elevation = elevation,
        enabled = enabled,
    ) {
        val padding = when (body) {
            ButtonBody.FIT -> 0.dp
            else -> 20.dp
        }
        Text(text = text, modifier = modifier.padding(horizontal = padding), color = textColors)
    }
}


@Preview
@Composable
fun PrimaryButtonPreview() {
    BalumTheme {
        Column {
            PrimaryButton(text = "Primary Button", onClick = {})
            PrimaryButton(text = "Secondary Button", onClick = {}, type = ButtonType.SECONDARY)
            PrimaryButton(text = "White Button", onClick = {}, type = ButtonType.WHITE)
            PrimaryButton(text = "White Button", onClick = {}, type = ButtonType.WHITE, body = ButtonBody.FIT)
        }
    }
}
