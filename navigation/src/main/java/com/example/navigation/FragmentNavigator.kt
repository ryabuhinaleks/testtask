package com.example.navigation

import androidx.fragment.app.FragmentManager

class FragmentNavigator(
    private val fragmentManager: FragmentManager,
    private val containerId: Int,
) {
    fun execute(command: FragmentCommand) {
        when (command) {
            is FragmentCommand.Forward -> forward(command)
            is FragmentCommand.Dialog -> dialog(command)
            is FragmentCommand.Replace -> replace(command)
        }
    }

    private fun dialog(command: FragmentCommand.Dialog) {
        val tag = command.screen.tag
        val dialog = command.screen.createDialogFragment().apply {
            arguments = command.screen.arguments
        }
        dialog.show(fragmentManager, tag)
    }

    private fun forward(command: FragmentCommand.Forward) {
        val tag = command.screen.tag
        val fragment = command.screen.createFragment().apply {
            arguments = command.screen.arguments
        }

        fragmentManager.beginTransaction()
            .replace(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    private fun replace(command: FragmentCommand.Replace) {
        val tag = command.screen.tag
        val fragment = command.screen.createFragment().apply {
            arguments = command.screen.arguments
        }
        clearBackStack()
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, tag)
            .commit()
    }

    private fun clearBackStack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}