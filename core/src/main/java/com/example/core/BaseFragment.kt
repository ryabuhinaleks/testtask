package com.example.core

import androidx.fragment.app.Fragment
import com.example.navigation.AppScreens
import com.example.navigation.FragmentCommand
import com.example.navigation.FragmentNavigator
import com.example.navigation.HasNavigator
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {

    protected val navigator: FragmentNavigator
        get() = (requireActivity() as HasNavigator).provideNavigator()

    protected val appScreens: AppScreens by inject()

    protected fun showError(retry: () -> Unit) {
        navigator.execute(
            FragmentCommand.Dialog(appScreens.getNetworkError(retry))
        )
    }
}
