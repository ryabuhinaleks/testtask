package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

interface FragmentScreen {
    val tag: String
    val arguments: Bundle?
    fun createFragment(): Fragment
}

interface DialogScreen {
    val tag: String
    val arguments: Bundle?
    fun createDialogFragment(): DialogFragment
}

interface AppScreens {
    fun getSearchCitiesScreen(): FragmentScreen
    fun getDetailedCityScreen(name: String, country: String, pop: Long): DialogScreen
    fun getNetworkError(retry: () -> Unit): DialogScreen
}