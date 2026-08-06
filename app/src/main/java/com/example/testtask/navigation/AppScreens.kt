package com.example.testtask.navigation

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.core_ui.components.error.NetworkErrorFragment
import com.example.navigation.AppScreens
import com.example.navigation.DialogScreen
import com.example.navigation.FragmentScreen
import com.example.search_cities.SearchCitiesNavigationProvider
import org.koin.core.component.KoinComponent

class AppScreensImpl : AppScreens, KoinComponent {

    override fun getSearchCitiesScreen(): FragmentScreen {
        return SearchCitiesNavigationProvider.provideSearchCitiesScreen()
    }

    override fun getDetailedCityScreen(name: String, country: String, pop: Long): DialogScreen {
        return SearchCitiesNavigationProvider.provideDetailedCityScreen(name, country, pop)
    }

    override fun getNetworkError(retry: () -> Unit): DialogScreen {
        return object : DialogScreen {
            override val tag: String = "NetworkErrorFragment"
            override val arguments: Bundle? = null
            override fun createDialogFragment(): DialogFragment {
                return NetworkErrorFragment().apply { onRetry = retry }
            }
        }
    }
}