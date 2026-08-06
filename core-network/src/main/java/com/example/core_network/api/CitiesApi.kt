package com.example.core_network.api

import com.example.core_network.models.CitiesRequest

interface CitiesApi {
    suspend fun getCities(query: String, page: Int): CitiesRequest
}