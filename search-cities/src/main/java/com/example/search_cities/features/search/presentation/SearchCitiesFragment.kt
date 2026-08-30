package com.example.search_cities.features.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import com.example.core.BaseFragment
import com.example.core.R
import com.example.core.model.City
import com.example.core_ui.components.divider.Divider
import com.example.core_ui.components.textfield.TextFieldSearchSimple
import com.example.core_ui.components.topbar.TopBar
import com.example.core_ui.theme.DesignTheme
import com.example.core_ui.theme.TesttaskTheme
import com.example.core_ui.theme.design.Dimens
import com.example.navigation.FragmentCommand
import com.example.search_cities.features.search.presentation.component.ItemCity
import com.example.search_cities.features.search.presentation.component.ItemCityShimmer
import com.example.search_cities.features.search.presentation.component.WidthType
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filterNot
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCitiesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val viewModel by viewModel<SearchCitiesViewModel>()
            val state = viewModel.container.stateFlow.collectAsState()

            TesttaskTheme {
                SearchCitiesContent(
                    state,
                    onCityClick = viewModel::onCityClick,
                    onSearchQueryChanged = viewModel::onSearchQueryChanged,
                    onLoadMore = viewModel::loadMore
                )
                LaunchedEffect(Unit) {
                    viewModel.container.sideEffectFlow.collect { sideEffect ->
                        when (sideEffect) {
                            is CitiesSideEffect.NavigateToDetails -> {
                                navigateToDetails(sideEffect.city)
                            }

                            is CitiesSideEffect.ShowNetworkError -> {
                                showError(viewModel::retry)
                            }
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SearchCitiesContent(
        state: State<UiState>,
        onCityClick: (City) -> Unit,
        onSearchQueryChanged: (String) -> Unit,
        onLoadMore: () -> Unit,
    ) {
        val searchQuery = rememberSaveable { mutableStateOf(state.value.searchQuery) }
        Scaffold(
            topBar = {
                Column {
                    TopBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.list_cities)
                    )
                    TextFieldSearchSimple(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Dimens.padding_16,
                                vertical = Dimens.padding_8
                            ),
                        placeholder = stringResource(R.string.search_hint_city_name),
                        value = searchQuery.value,
                        onValueChange = { newText ->
                            searchQuery.value = newText
                            onSearchQueryChanged(newText)
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding(),
            containerColor = DesignTheme.colors.backgroundPrimary
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (val uiState = state.value) {
                    is UiState.Loading -> {
                        Loading()
                    }

                    is UiState.Content -> {
                        Content(uiState, onCityClick, onLoadMore)
                    }
                }
            }
        }
    }

    @Composable
    private fun Loading() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.padding_16)
        ) {
            ItemCityShimmer()
            Divider()
            ItemCityShimmer(widthType = WidthType.LONG)
            Divider()
            ItemCityShimmer()
            Divider()
            ItemCityShimmer(widthType = WidthType.LONG)
            Divider()
            ItemCityShimmer()
        }
    }

    @OptIn(FlowPreview::class)
    @Composable
    private fun Content(
        content: UiState.Content,
        onCityClick: (City) -> Unit,
        onLoadMore: () -> Unit,
    ) {
        val scrollState = rememberLazyListState()
        val currentContent by rememberUpdatedState(content)
        LaunchedEffect(Unit) {
            snapshotFlow { scrollState.layoutInfo }
                .debounce(200)
                .filterNot {
                    currentContent.isLoadingMore || currentContent.isEndReached || currentContent.items.isEmpty()
                }
                .collect { layoutInfo ->
                    val totalItemsCount = layoutInfo.totalItemsCount
                    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                    val threshold = (totalItemsCount * LOAD_MORE_THRESHOLD).toInt()
                    if (lastVisibleItemIndex >= threshold) {
                        onLoadMore()
                    }
                }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.padding_16),
            state = scrollState
        ) {
            itemsIndexed(
                items = content.items,
                key = { _, city -> city.id }
            ) { index, city ->
                ItemCity(city = city) { onCityClick(city) }
                if (index < content.items.size - 1)
                    Divider()
            }
        }
    }

    private fun navigateToDetails(city: City) {
        navigator.execute(
            FragmentCommand.Dialog(
                appScreens.getDetailedCityScreen(city.name, city.country, city.pop)
            )
        )
    }

    companion object {
        private const val LOAD_MORE_THRESHOLD = 0.85
    }
}