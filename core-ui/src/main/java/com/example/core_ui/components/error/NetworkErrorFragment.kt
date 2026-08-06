package com.example.core_ui.components.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import com.example.core.BaseDialogFragment
import com.example.core.R
import com.example.core_ui.theme.TesttaskTheme

class NetworkErrorFragment : BaseDialogFragment() {

    var onRetry: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            TesttaskTheme {
                ErrorScreen(
                    title = stringResource(R.string.network_error_title),
                    message = stringResource(R.string.network_error_message),
                    buttonName = stringResource(R.string.retry_button),
                    onRetry = {
                        onRetry?.invoke()
                        dismiss()
                    }
                )
            }
        }
    }
}