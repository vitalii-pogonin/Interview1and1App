package de.oneandone.salesmanandroidapp.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import dagger.hilt.android.internal.managers.ViewComponentManager.FragmentContextWrapper

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0B3A83),
    onPrimary = Color.White,
    secondary = Color.Black,
    tertiary = Color(0xFF999999),

    surface = Color.White,
    onSurface = Color(0xFFC6C5C9),
    surfaceVariant = Color(0xFFEFEFEF),
    onSurfaceVariant = Color(0xFFE3E8F2)
)

@Composable
fun SalesmanTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = ((view.context as FragmentContextWrapper).baseContext as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}