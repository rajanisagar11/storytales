package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = NeonPurple,
    onPrimary = Color.White,
    primaryContainer = ElectricViolet,
    secondary = CoralRose,
    onSecondary = Color.White,
    tertiary = AmberGold,
    background = DeepObsidian,
    onBackground = DarkTextPrimary,
    surface = NightSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = GlassCard,
    onSurfaceVariant = DarkTextSecondary
)

@Composable
fun ReelTalesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
