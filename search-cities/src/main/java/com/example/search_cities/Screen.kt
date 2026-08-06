package com.example.search_cities

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.navigation.DialogScreen
import com.example.navigation.FragmentScreen
import com.example.search_cities.features.detail.presentation.DetailedCityFragment
import com.example.search_cities.features.detail.presentation.DetailedCityFragment.Companion.COUNTRY
import com.example.search_cities.features.detail.presentation.DetailedCityFragment.Companion.NAME
import com.example.search_cities.features.detail.presentation.DetailedCityFragment.Companion.POP
import com.example.search_cities.features.search.presentation.SearchCitiesFragment

internal class SearchCitiesScreen : FragmentScreen {
    override val tag: String = "SearchCitiesFragment"
    override val arguments: Bundle? = null
    override fun createFragment(): Fragment = SearchCitiesFragment()
}

internal class DetailedCityScreen(name: String, country: String, pop: Long) : DialogScreen {
    override val tag: String = "DetailedCityFragment"
    override val arguments: Bundle = Bundle().apply {
        putString(NAME, name)
        putString(COUNTRY, country)
        putLong(POP, pop)
    }

    override fun createDialogFragment(): DialogFragment = DetailedCityFragment()
}
