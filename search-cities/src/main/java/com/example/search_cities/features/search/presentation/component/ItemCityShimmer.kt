package com.example.search_cities.features.search.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.R
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.design.Dimens
import com.eygraber.compose.placeholder.PlaceholderHighlight
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.material3.shimmer

@Composable
internal fun ItemCityShimmer(
    widthType: WidthType = WidthType.MIDDLE,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DesignTheme.colors.backgroundPrimary)
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
        Box(
            modifier = Modifier
                .fillMaxWidth(widthType.percent)
                .height(Dimens.padding_30)
                .padding(vertical = Dimens.padding_4)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer(),
                    color = DesignTheme.colors.shimmer
                )
                .clip(RoundedCornerShape(Dimens.padding_12))
        )
    }
}

internal enum class WidthType(val percent: Float) {
    MIDDLE(0.5f),
    LONG(0.6f)
}

@Preview(showBackground = true)
@Composable
private fun ItemCityShimmerTest() {
    Column {
        ItemCityShimmer()
        ItemCityShimmer(widthType = WidthType.LONG)
    }
}