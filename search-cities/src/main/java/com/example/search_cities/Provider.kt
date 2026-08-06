package com.example.search_cities

import com.example.navigation.DialogScreen
import com.example.navigation.FragmentScreen

object SearchCitiesNavigationProvider {
    fun provideSearchCitiesScreen(): FragmentScreen = SearchCitiesScreen()
    fun provideDetailedCityScreen(name: String, country: String, pop: Long): DialogScreen =
        DetailedCityScreen(name, country, pop)
}
