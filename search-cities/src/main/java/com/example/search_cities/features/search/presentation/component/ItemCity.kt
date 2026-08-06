package com.example.search_cities.features.search.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.model.City
import com.example.core_ui.R
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.design.Dimens

@Composable
internal fun ItemCity(
    modifier: Modifier = Modifier,
    city: City,
    onCityClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DesignTheme.colors.backgroundPrimary)
            .clickable { onCityClick() }
            .padding(vertical = Dimens.padding_12),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(Dimens.icon_24),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_location),
            contentDescription = null,
            tint = DesignTheme.colors.iconSecondary
        )
        Spacer(modifier = Modifier.width(Dimens.padding_12))
        Text(
            text = city.toDescription(),
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.padding_30)
                .padding(vertical = Dimens.padding_4),
            color = DesignTheme.colors.textPrimary,
            style = DesignTheme.typography.subtitle1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemCityTest() {
    val city = City(1, "Москва", "Россия", 123)
    Column {
        ItemCity(city = city) {}
    }
}