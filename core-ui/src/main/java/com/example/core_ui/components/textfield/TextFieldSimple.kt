package com.example.core_ui.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.R
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.TesttaskTheme
import com.example.core_ui.theme.design.Dimens

@Composable
fun TextFieldSearchSimple(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = DesignTheme.typography.title4,
        placeholder = placeholder?.let {
            {
                Text(
                    text = it,
                    style = DesignTheme.typography.subtitle1
                )
            }
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.size(Dimens.icon_24),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = DesignTheme.colors.iconPrimary
            )
        },
        shape = RoundedCornerShape(Dimens.radius_16),
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedContainerColor = DesignTheme.colors.backgroundPrimary,
            unfocusedContainerColor = DesignTheme.colors.backgroundTertiary,
            focusedIndicatorColor = DesignTheme.colors.accentBrand,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = DesignTheme.colors.textPrimary,
            unfocusedTextColor = DesignTheme.colors.textPrimary,
            focusedPlaceholderColor = DesignTheme.colors.textTertiary,
            unfocusedPlaceholderColor = DesignTheme.colors.textTertiary
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun TextFieldSearchSimpleTest() {
    TesttaskTheme {
        TextFieldSearchSimple(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            placeholder = "Введите название города",
            onValueChange = {}
        )
    }
}