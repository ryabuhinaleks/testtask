package com.example.core_network.models

import kotlinx.serialization.Serializable

@Serializable
data class CityRequest(
    val id: Int,
    val name: String,
    val country: String,
    val pop: Long,
)

@Serializable
data class CitiesRequest(
    val items: List<CityRequest>,
    val total: Int,
)