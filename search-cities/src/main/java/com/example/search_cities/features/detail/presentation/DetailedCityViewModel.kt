package com.example.search_cities.features.detail.presentation

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.core.R
import com.example.search_cities.features.detail.presentation.DetailedCityFragment.Companion.COUNTRY
import com.example.search_cities.features.detail.presentation.DetailedCityFragment.Companion.NAME
import com.example.search_cities.features.detail.presentation.DetailedCityFragment.Companion.POP
import org.koin.core.component.KoinComponent
import org.orbitmvi.orbit.OrbitContainerHost
import org.orbitmvi.orbit.viewmodel.orbitContainer

class DetailedCityViewModel(
    savedStateHandle: SavedStateHandle,
    private val appContext: Context,
) : OrbitContainerHost<UiState, UiState, DetailedCitySideEffect>, ViewModel(), KoinComponent {

    override val container = orbitContainer<UiState, DetailedCitySideEffect>(UiState.Idle)

    val city: String = savedStateHandle[NAME] ?: ""
    val country: String = savedStateHandle[COUNTRY] ?: ""
    val pop: Long = savedStateHandle[POP] ?: 0

    init {
        intent {
            reduce {
                UiState.Content(
                    city = city,
                    country = country,
                    pop = pop.formatPopulation(appContext.getString(R.string.people_short))
                )
            }
        }
    }

    fun openUrl(city: String) {
        intent {
            postSideEffect(DetailedCitySideEffect.OpenUrl(city))
        }
    }

    fun back() {
        intent {
            postSideEffect(DetailedCitySideEffect.NavigateToBack)
        }
    }
}
