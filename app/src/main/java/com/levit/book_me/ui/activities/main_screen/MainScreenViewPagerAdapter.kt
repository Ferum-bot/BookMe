package com.levit.book_me.ui.activities.main_screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.levit.book_me.ui.fragments.main_screens.chats.MainScreenChatsFragment
import com.levit.book_me.ui.fragments.main_screens.current_friend_profile.MainScreenFriendProfileFragment
import com.levit.book_me.ui.fragments.main_screens.user_profile.MainScreenUserProfileFragment
import com.levit.book_me.ui.fragments.main_screens.user_profile.MainScreenUserProfileViewModel

class MainScreenViewPagerAdapter(
    fragmentActivity: FragmentActivity,
): FragmentStateAdapter(fragmentActivity) {

    companion object {

        const val CHATS_FRAGMENT_POSITION = 2
        const val CURRENT_FRIEND_FRAGMENT_POSITION = 1
        const val PROFILE_FRAGMENT_POSITION = 0

        private const val FRAGMENT_COUNT = 3
    }

    override fun getItemCount() = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment =
    when(position) {
        PROFILE_FRAGMENT_POSITION -> {
            MainScreenUserProfileFragment()
        }
        CURRENT_FRIEND_FRAGMENT_POSITION -> {
            MainScreenFriendProfileFragment()
        }
        CHATS_FRAGMENT_POSITION -> {
            MainScreenChatsFragment()
        }
        else -> {
            throw IllegalArgumentException("Invalid fragment to create from position: $position")
        }
    }

}