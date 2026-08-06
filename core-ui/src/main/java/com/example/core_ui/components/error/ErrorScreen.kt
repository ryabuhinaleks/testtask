package com.example.core_ui.components.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.R
import com.example.core_ui.components.button.ButtonSimple
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.design.Dimens

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    buttonName: String,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                modifier = Modifier
                    .size(Dimens.icon_88)
                    .align(Alignment.CenterHorizontally),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_processing_loader),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.padding_32)
                    .padding(top = Dimens.padding_24),
                text = title,
                style = DesignTheme.typography.title2,
                color = DesignTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.padding_32)
                    .padding(top = Dimens.padding_16),
                text = message,
                style = DesignTheme.typography.body1,
                color = DesignTheme.colors.textPrimary,
                textAlign = TextAlign.Center
            )
        }

        ButtonSimple(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_16)
                .align(Alignment.BottomCenter),
            text = buttonName,
            onClick = onRetry
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorTest() {
    ErrorScreen(
        modifier = Modifier,
        title = "Нет подключения к интернету",
        message = "Проверьте соединение и попробуйте снова. Без интернета данные не загрузятся",
        buttonName = "Попробовать снова"
    ) {}
}