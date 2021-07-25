package com.levit.book_me.ui.activities.main_screen

import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.MainScreenInteractor
import com.levit.book_me.ui.base.BaseViewModel
import javax.inject.Inject

@MainScreenScope
class MainScreenActivityViewModel @Inject constructor(
    private val interactor: MainScreenInteractor
): BaseViewModel() {

    fun searchNewFriend() {

    }

    fun openInterlocutorProfile(interlocutorId: Long) {

    }

    fun openChatWithInterlocutor(interlocutorId: Long) {

    }

}