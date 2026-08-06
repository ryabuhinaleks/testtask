package com.example.search_cities.features.search.domain

import com.example.core_network.models.CitiesRequest

interface SearchCitiesRepository {
    suspend fun searchCities(query: String, page: Int): CitiesRequest
}