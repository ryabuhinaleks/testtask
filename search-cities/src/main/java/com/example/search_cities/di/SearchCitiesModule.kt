package com.example.search_cities.di

import com.example.search_cities.features.detail.presentation.DetailedCityViewModel
import com.example.search_cities.features.search.data.SearchCitiesInteractorImpl
import com.example.search_cities.features.search.data.SearchCitiesRepositoryImpl
import com.example.search_cities.features.search.domain.SearchCitiesInteractor
import com.example.search_cities.features.search.domain.SearchCitiesRepository
import com.example.search_cities.features.search.presentation.SearchCitiesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchCitiesModule = module {
    single<SearchCitiesRepository> { SearchCitiesRepositoryImpl(get()) }
    single<SearchCitiesInteractor> { SearchCitiesInteractorImpl(get()) }

    viewModel { SearchCitiesViewModel(get()) }
    viewModel { DetailedCityViewModel(get(), get()) }
}
