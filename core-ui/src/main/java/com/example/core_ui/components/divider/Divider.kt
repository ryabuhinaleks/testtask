package com.example.core_ui.components.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.DesignTheme

@Composable
fun Divider(
    color: Color = DesignTheme.colors.dividerPrimary,
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(
        color = color,
        thickness = 1.dp,
        modifier = modifier
    )
}