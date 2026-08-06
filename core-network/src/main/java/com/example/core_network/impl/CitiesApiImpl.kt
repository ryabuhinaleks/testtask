package com.example.core_network.impl

import com.example.core_network.api.CitiesApi
import com.example.core_network.models.CitiesRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

internal class CitiesApiImpl(
    private val client: HttpClient,
) : CitiesApi {

    override suspend fun getCities(
        query: String,
        page: Int
    ): CitiesRequest {
        return client.get {
            url("http://dev-dep.tools.urent.tech:8080/api/cities")
            parameter("query", query)
            parameter("page", page)
        }.body<CitiesRequest>()
    }
}