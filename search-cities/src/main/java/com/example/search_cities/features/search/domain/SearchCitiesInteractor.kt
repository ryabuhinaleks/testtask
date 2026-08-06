package com.example.search_cities.features.search.domain

import com.example.core.model.Cities

interface SearchCitiesInteractor {
    suspend fun searchCities(query: String, page: Int): Cities
}