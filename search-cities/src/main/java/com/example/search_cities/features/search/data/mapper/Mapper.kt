package com.example.search_cities.features.search.data.mapper

import com.example.core.model.Cities
import com.example.core.model.City
import com.example.core_network.models.CitiesRequest
import com.example.core_network.models.CityRequest

fun CitiesRequest.toDomain() = Cities(
    items = items.map { it.toDomain() },
    total = total
)

fun CityRequest.toDomain() = City(id, name, country, pop)