package com.example.search_cities.features.detail.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.example.core.BaseDialogFragment
import com.example.core.R
import com.example.core_ui.components.button.ButtonSimple
import com.example.core_ui.components.topbar.TopBar
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.TesttaskTheme
import com.example.core_ui.theme.design.Dimens
import com.example.search_cities.features.detail.presentation.UiState.Content
import com.example.search_cities.features.detail.presentation.UiState.Idle
import com.example.search_cities.features.detail.presentation.component.DetailInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedCityFragment : BaseDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val viewModel by viewModel<DetailedCityViewModel>()
            val state = viewModel.container.stateFlow.collectAsState()

            TesttaskTheme {
                DetailedCityContent(state, openUrl = viewModel::openUrl, back = viewModel::back)
                LaunchedEffect(Unit) {
                    viewModel.container.sideEffectFlow.collect { sideEffect ->
                        when (sideEffect) {
                            DetailedCitySideEffect.NavigateToBack -> {
                                dismiss()
                            }

                            is DetailedCitySideEffect.OpenUrl -> {
                                openUrl(city = sideEffect.city)
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DetailedCityContent(
        state: State<UiState>,
        openUrl: (String) -> Unit,
        back: () -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = stringResource(R.string.city_information),
                    back = back
                )
            },
            modifier = Modifier.safeContentPadding(),
            containerColor = DesignTheme.colors.backgroundPrimary
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(
                        start = Dimens.padding_16,
                        end = Dimens.padding_16,
                        top = Dimens.padding_8
                    )
            ) {
                when (val uiState = state.value) {
                    is Content -> {
                        Column {
                            DetailInfo(
                                title = stringResource(R.string.city_label),
                                subtitle = uiState.city
                            )
                            DetailInfo(
                                title = stringResource(R.string.country_label),
                                subtitle = uiState.country
                            )
                            DetailInfo(
                                title = stringResource(R.string.population_label),
                                subtitle = uiState.pop
                            )
                        }
                        ButtonSimple(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = Dimens.padding_16)
                                .align(Alignment.BottomCenter),
                            text = stringResource(R.string.search_city_info_hint),
                        ) { openUrl(uiState.city) }
                    }

                    Idle -> {}
                }
            }
        }
    }

    private fun openUrl(city: String) {
        val intent = Intent(Intent.ACTION_VIEW, (URL + city).toUri())
        startActivity(intent)
    }

    companion object {
        private const val URL = "https://yandex.ru/search/?text="

        const val NAME = "NAME"
        const val COUNTRY = "COUNTRY"
        const val POP = "POP"
    }
}