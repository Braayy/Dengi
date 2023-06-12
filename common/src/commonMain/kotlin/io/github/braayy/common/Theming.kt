package io.github.braayy.common

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colors = Colors(
    primary = Color(0xFF358C6F),
    primaryVariant = Color(0xFF359491),
    secondary = Color(0xFF525252),
    secondaryVariant = Color(0xff6a6a6a),
    background = Color(0xFF313131),
    surface = Color(0xFF414141),
    error = Color(0xFFA64545),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White,
    isLight = false,
)

@get:Composable
val Colors.modalBackground
    get() = Color(0xFF232323)

@Composable
fun DengiTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = colors,
        content = content,
    )
}