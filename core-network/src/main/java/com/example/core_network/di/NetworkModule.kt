package com.example.core_network.di

import com.example.core_network.NetworkClient
import com.example.core_network.api.CitiesApi
import com.example.core_network.impl.CitiesApiImpl
import org.koin.dsl.module

val networkModule = module {
    single {
        NetworkClient.create()
    }

    single<CitiesApi> {
        CitiesApiImpl(get())
    }
}