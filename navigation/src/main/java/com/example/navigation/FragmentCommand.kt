package com.example.navigation

sealed class FragmentCommand {
    data class Dialog(val screen: DialogScreen) : FragmentCommand()
    data class Forward(val screen: FragmentScreen) : FragmentCommand()
    data class Replace(val screen: FragmentScreen) : FragmentCommand()
}