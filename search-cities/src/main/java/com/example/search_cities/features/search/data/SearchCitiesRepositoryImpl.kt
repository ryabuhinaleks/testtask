package com.example.search_cities.features.search.data

import com.example.core_network.api.CitiesApi
import com.example.core_network.models.CitiesRequest
import com.example.search_cities.features.search.domain.SearchCitiesRepository

class SearchCitiesRepositoryImpl(
    private val api: CitiesApi,
) : SearchCitiesRepository {

    override suspend fun searchCities(query: String, page: Int): CitiesRequest {
        return api.getCities(query, page)
    }
}