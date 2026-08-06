package com.example.core_ui.theme.design

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class DesignColors(
    val textPrimary: Color,
    val textTertiary: Color,
    val iconSecondary: Color,
    val iconPrimary: Color,

    val backgroundBrand: Color,
    val accentBrand: Color,
    val dividerPrimary: Color,
    val shimmerBase: Color,
    val backgroundPrimary: Color,
    val shimmer: Color,

    val buttonPrimaryBackground: Color,
    val buttonPrimaryBackgroundPressed: Color,
    val buttonPrimaryForeground: Color,
    val backgroundTertiary: Color,
) {
    companion object {
        fun light(): DesignColors = DesignColors(
            textPrimary = Color(0xFF25222B),
            textTertiary = Color(0xFFA5A2AB),
            iconSecondary = Color(0xFF777381),
            iconPrimary = Color(0xFF25222B),
            backgroundBrand = Color(0xFFF5EDFF),
            accentBrand = Color(0xFF804AFF),
            dividerPrimary = Color(0xFFEFEFF0),
            shimmerBase = Color(0x14000000),
            backgroundPrimary = Color(0xFFFFFFFF),
            shimmer = Color(0x14000000),
            buttonPrimaryBackground = Color(0xFF804AFF),
            buttonPrimaryBackgroundPressed = Color(0xFF683BD3),
            buttonPrimaryForeground = Color(0xFFFFFFFF),
            backgroundTertiary = Color(0xFFF6F6F7),
        )

        fun dark(): DesignColors = DesignColors(
            textPrimary = Color(0xFF25222B),
            textTertiary = Color(0xFFA5A2AB),
            iconSecondary = Color(0xFF777381),
            iconPrimary = Color(0xFF25222B),
            backgroundBrand = Color(0xFFF5EDFF),
            accentBrand = Color(0xFF804AFF),
            dividerPrimary = Color(0xFFEFEFF0),
            shimmerBase = Color(0x14000000),
            backgroundPrimary = Color(0xFFFFFFFF),
            shimmer = Color(0x14000000),
            buttonPrimaryBackground = Color(0xFF804AFF),
            buttonPrimaryBackgroundPressed = Color(0xFF683BD3),
            buttonPrimaryForeground = Color(0xFFFFFFFF),
            backgroundTertiary = Color(0xFFF6F6F7),
        )
    }
}

internal val LocalDesignColors = staticCompositionLocalOf { DesignColors.light() }

