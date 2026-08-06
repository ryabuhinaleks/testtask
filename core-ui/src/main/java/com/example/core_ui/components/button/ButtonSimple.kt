package com.example.core_ui.components.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.design.Dimens

@Composable
fun ButtonSimple(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        modifier = modifier.heightIn(min = Dimens.size_56),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor =
                if (isPressed) {
                    DesignTheme.colors.buttonPrimaryBackgroundPressed
                } else {
                    DesignTheme.colors.buttonPrimaryBackground
                },
            contentColor = DesignTheme.colors.buttonPrimaryForeground
        ),
        shape = RoundedCornerShape(Dimens.radius_16),
        contentPadding = PaddingValues(
            start = Dimens.padding_24,
            top = Dimens.padding_16,
            end = Dimens.padding_24,
            bottom = Dimens.padding_16,
        ),
        interactionSource = interactionSource
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            style = DesignTheme.typography.title4,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonSimpleTest() {
    ButtonSimple(
        text = "Поиск информации о городе"
    ) {}
}