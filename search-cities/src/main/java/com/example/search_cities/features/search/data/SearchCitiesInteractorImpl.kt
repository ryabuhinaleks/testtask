package com.example.search_cities.features.search.data

import com.example.core.model.Cities
import com.example.search_cities.features.search.data.mapper.toDomain
import com.example.search_cities.features.search.domain.SearchCitiesInteractor
import com.example.search_cities.features.search.domain.SearchCitiesRepository

class SearchCitiesInteractorImpl(
    private val repository: SearchCitiesRepository,
) : SearchCitiesInteractor {

    override suspend fun searchCities(query: String, page: Int): Cities {
        return repository.searchCities(query, page).toDomain()
    }
}