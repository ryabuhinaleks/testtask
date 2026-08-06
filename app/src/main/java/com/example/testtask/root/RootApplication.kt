package com.example.testtask.root

import android.app.Application
import com.example.core_network.di.networkModule
import com.example.search_cities.di.searchCitiesModule
import com.example.testtask.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RootApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RootApplication)
            modules(appModule, networkModule, searchCitiesModule)
        }
    }
}
