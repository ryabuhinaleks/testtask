package com.example.search_cities.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.City
import com.example.search_cities.features.search.domain.SearchCitiesInteractor
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.OrbitContainerHost
import org.orbitmvi.orbit.viewmodel.orbitContainer

class SearchCitiesViewModel(
    private val interactor: SearchCitiesInteractor,
) : OrbitContainerHost<UiState, UiState, CitiesSideEffect>, ViewModel(), KoinComponent {

    private var searchJob: Job? = null
    private var currentPage = 1

    override val container = orbitContainer<UiState, CitiesSideEffect>(UiState.Loading())

    init {
        loadCities("")
    }

    fun onSearchQueryChanged(query: String) = intent {
        val currentState = state
        reduce {
            when (currentState) {
                is UiState.Loading -> currentState.copy(searchQuery = query)
                is UiState.Content -> currentState.copy(searchQuery = query)
            }
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DELAY)
            currentPage = 1
            loadCities(query)
        }
    }

    fun loadMore() = intent {
        val currentState = state as? UiState.Content ?: return@intent

        if (currentState.isLoadingMore || currentState.isEndReached || currentState.items.isEmpty())
            return@intent

        currentPage++
        loadCities(currentState.searchQuery)
    }

    fun onCityClick(city: City) {
        intent {
            postSideEffect(CitiesSideEffect.NavigateToDetails(city))
        }
    }

    fun retry() = intent {
        currentPage = 1
        loadCities(state.searchQuery)
    }

    private fun loadCities(query: String) = intent {
        reduce {
            if (currentPage == 1) {
                UiState.Loading(searchQuery = query)
            } else {
                val currentState = state as? UiState.Content
                currentState?.copy(isLoadingMore = true) ?: UiState.Loading(query)
            }
        }

        try {
            val data = interactor.searchCities(query, currentPage)
            val isEndReached = data.items.size >= data.total
            val newItems = data.items.toImmutableList()
            if (currentPage == 1) {
                reduce {
                    UiState.Content(
                        searchQuery = query,
                        items = newItems,
                        total = data.total,
                        isLoadingMore = false,
                        isEndReached = isEndReached
                    )
                }
            } else {
                reduce {
                    val currentItems = (state as? UiState.Content)?.items ?: persistentListOf()
                    UiState.Content(
                        searchQuery = query,
                        items = (currentItems + newItems).toImmutableList(),
                        total = data.total,
                        isLoadingMore = false,
                        isEndReached = isEndReached
                    )
                }
            }
        } catch (e: Exception) {
            postSideEffect(CitiesSideEffect.ShowNetworkError)
        }
    }

    companion object {
        private const val DELAY = 500L
    }
}
