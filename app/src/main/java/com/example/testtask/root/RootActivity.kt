package com.example.testtask.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.AppScreens
import com.example.navigation.FragmentCommand
import com.example.navigation.FragmentNavigator
import com.example.navigation.HasNavigator
import com.example.testtask.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RootActivity : AppCompatActivity(), HasNavigator {

    private val navigator: FragmentNavigator by inject { parametersOf(this) }
    private val appScreens: AppScreens by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showInitialFragment()
    }

    override fun provideNavigator(): FragmentNavigator = navigator

    private fun showInitialFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            navigator.execute(
                FragmentCommand.Replace(appScreens.getSearchCitiesScreen())
            )
        }
    }
}

