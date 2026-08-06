package com.example.core.model

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val pop: Long,
) {
    fun toDescription() = "$country, $name"
}

data class Cities(
    val items: List<City>,
    val total: Int,
)