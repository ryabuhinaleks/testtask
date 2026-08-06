package com.example.search_cities.features.search.presentation

import com.example.core.model.City
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

sealed class UiState(open val searchQuery: String) {
    data class Loading(
        override val searchQuery: String = "",
    ) : UiState(searchQuery)

    data class Content(
        override val searchQuery: String = "",
        val items: ImmutableList<City> = persistentListOf(),
        val total: Int = 0,
        val isLoadingMore: Boolean = false,
        val isEndReached: Boolean = false,
    ) : UiState(searchQuery)
}

sealed class CitiesSideEffect {
    data class NavigateToDetails(val city: City) : CitiesSideEffect()
    object ShowNetworkError : CitiesSideEffect()
}