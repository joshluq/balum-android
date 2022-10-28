package pe.joshluq.balum.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pe.joshluq.balum.R

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(Font(R.font.titillium_web_regular)),
    button = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    )
    /* Other default text styles to override

    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)