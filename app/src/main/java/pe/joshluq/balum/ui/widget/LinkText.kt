package pe.joshluq.balum.ui.widget

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import pe.joshluq.balum.ui.theme.BalumTheme

@Composable
fun LinkText(
    text: String,
    linkText: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    linkColor: Color = MaterialTheme.colors.primary,
    onClick: (String) -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(
                color = color,
                fontFamily = MaterialTheme.typography.button.fontFamily
            ),
            start = 0,
            end = text.length
        )
        val startIndex = text.indexOf(linkText)
        val endIndex = startIndex + linkText.length
        addStyle(
            style = SpanStyle(
                color = linkColor,
                fontWeight = FontWeight.Bold
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "LINK",
            annotation = linkText,
            start = startIndex,
            end = endIndex
        )
    }
    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = {
            annotatedString.getStringAnnotations("LINK", it, it).firstOrNull()?.let { stringRange ->
                    onClick(stringRange.item)
                }
        }
    )
}


@Preview
@Composable
fun TextLinkPreview() {
    BalumTheme {
        LinkText(
            text = "Full text",
            linkText = "text",
            color = Color.White
        ) {

        }
    }
}