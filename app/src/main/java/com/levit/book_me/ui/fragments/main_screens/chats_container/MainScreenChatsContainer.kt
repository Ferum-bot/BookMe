package com.levit.book_me.ui.fragments.main_screens.chats_container

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.levit.book_me.R
import com.levit.book_me.ui.fragments.utills.BackPressedOwner

class MainScreenChatsContainer:
    Fragment(R.layout.fragment_main_screen_chat_container), BackPressedOwner {


    override fun onBackPressed(): Boolean {
        val hostFragment = childFragmentManager.fragments.getOrNull(0)
            ?: return false
        if (hostFragment !is NavHostFragment) {
            return false
        }

        val fragments = hostFragment.childFragmentManager.fragments
        var backPressedResult = false

        fragments.mapNotNull { fragment ->
            fragment as? BackPressedOwner
        }.forEach { fragment ->
            val backPressedHandled = fragment.onBackPressed()
            backPressedResult = backPressedResult or backPressedHandled
        }

        return backPressedResult
    }
}