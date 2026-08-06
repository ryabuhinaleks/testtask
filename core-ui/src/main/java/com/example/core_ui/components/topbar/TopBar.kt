package com.example.core_ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.R
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.TesttaskTheme
import com.example.core_ui.theme.design.Dimens

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    back: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .background(DesignTheme.colors.backgroundPrimary)
            .padding(top = Dimens.padding_12, bottom = Dimens.padding_16)
    ) {
        back?.let {
            Icon(
                modifier = Modifier
                    .padding(start = Dimens.padding_16)
                    .clickable { back() }
                    .size(Dimens.icon_24),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = DesignTheme.colors.iconPrimary
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = DesignTheme.typography.title3,
            color = DesignTheme.colors.textPrimary,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ButtonSimpleTest() {
    TesttaskTheme {
        TopBar(
            title = "Список городов",
            back = {}
        )
    }
}