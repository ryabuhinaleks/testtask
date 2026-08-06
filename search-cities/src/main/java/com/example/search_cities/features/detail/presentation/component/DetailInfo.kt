package com.example.search_cities.features.detail.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.design.Dimens

@Composable
internal fun DetailInfo(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.padding_10)
    ) {
        Text(
            text = title,
            style = DesignTheme.typography.subtitle1,
            color = DesignTheme.colors.textPrimary
        )
        Text(
            modifier = Modifier.padding(top = Dimens.padding_2),
            text = subtitle,
            style = DesignTheme.typography.body1,
            color = DesignTheme.colors.textPrimary
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailInfoTest() {
    DetailInfo(
        title = "Город",
        subtitle = "Москва"
    )
}