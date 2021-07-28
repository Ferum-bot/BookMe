package com.levit.book_me.ui.activities.main_screen

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.levit.book_me.ui.BundleConstants
import com.levit.book_me.ui.fragments.main_screens.chats.MainScreenChatsFragment
import com.levit.book_me.ui.fragments.main_screens.current_chat.MainScreenCurrentChatFragment
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

    private var userFragment: MainScreenUserProfileFragment? = null
    private var interlocutorFragment: MainScreenFriendProfileFragment? = null
    private var generalChatsFragment: MainScreenChatsFragment? = null
    private var currentChatFragment: MainScreenCurrentChatFragment? = null

    private var openGeneralChats = true

    override fun getItemCount() = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment =
    when(position) {
        PROFILE_FRAGMENT_POSITION -> {
            userFragment = MainScreenUserProfileFragment()
            userFragment!!
        }
        CURRENT_FRIEND_FRAGMENT_POSITION -> {
            interlocutorFragment = MainScreenFriendProfileFragment()
            interlocutorFragment!!
        }
        CHATS_FRAGMENT_POSITION -> {
            if (openGeneralChats) {
                generalChatsFragment = MainScreenChatsFragment()
                generalChatsFragment!!
            } else {
                currentChatFragment ?: MainScreenCurrentChatFragment()
            }
        }
        else -> {
            throw IllegalArgumentException("Invalid fragment to create from position: $position")
        }
    }

    fun openCurrentChat(chatId: Long, interlocutorId: Long) {
        openGeneralChats = false
        currentChatFragment = MainScreenCurrentChatFragment()
        currentChatFragment?.arguments = bundleOf(
            BundleConstants.CURRENT_CHAT_ID_NAME to chatId,
            BundleConstants.CURRENT_INTERLOCUTOR_ID_NAME to interlocutorId,
        )
        notifyItemChanged(CHATS_FRAGMENT_POSITION)
    }

    fun openGeneralChats() {
        openGeneralChats = true
        notifyItemChanged(CHATS_FRAGMENT_POSITION)
    }

    fun openInterlocutorProfile(interlocutorId: Long) {
        interlocutorFragment = MainScreenFriendProfileFragment()
        interlocutorFragment?.arguments = bundleOf(
            BundleConstants.CURRENT_INTERLOCUTOR_ID_NAME to interlocutorId
        )
        notifyItemChanged(CURRENT_FRIEND_FRAGMENT_POSITION)
    }
}