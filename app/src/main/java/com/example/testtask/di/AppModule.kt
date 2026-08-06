package com.example.testtask.di

import androidx.fragment.app.FragmentActivity
import com.example.navigation.AppScreens
import com.example.navigation.FragmentNavigator
import com.example.testtask.R
import com.example.testtask.navigation.AppScreensImpl
import org.koin.dsl.module

val appModule = module {

    single<AppScreens> {
        AppScreensImpl()
    }

    single { (activity: FragmentActivity) ->
        FragmentNavigator(
            fragmentManager = activity.supportFragmentManager,
            containerId = R.id.fragment_container
        )
    }
}