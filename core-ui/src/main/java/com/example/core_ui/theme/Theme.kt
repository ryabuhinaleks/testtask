package com.example.core_ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.example.core_ui.theme.design.DesignColors
import com.example.core_ui.theme.design.DesignTypography
import com.example.core_ui.theme.design.LocalDesignColors
import com.example.core_ui.theme.design.LocalTypography

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

object DesignTheme {
    val colors: DesignColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDesignColors.current

    val typography: DesignTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current
}

@Composable
fun TesttaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        LocalDesignColors provides if (darkTheme) DesignColors.dark() else DesignColors.light(),
        LocalTypography provides DesignTypography
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}