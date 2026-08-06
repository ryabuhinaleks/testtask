package com.example.search_cities.features.detail.presentation

sealed class UiState {
    object Idle : UiState()

    data class Content(
        val city: String,
        val country: String,
        val pop: String,
    ) : UiState()
}

sealed class DetailedCitySideEffect {
    object NavigateToBack : DetailedCitySideEffect()
    data class OpenUrl(val city: String) : DetailedCitySideEffect()
}