package com.stream_suite.flashlight.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colors = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White

    /* Other default colors to override
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun FlashlightTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}