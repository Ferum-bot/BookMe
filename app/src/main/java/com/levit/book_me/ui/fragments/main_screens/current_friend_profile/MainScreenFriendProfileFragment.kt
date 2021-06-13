package com.levit.book_me.ui.fragments.main_screens.current_friend_profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.ui.base.BaseMainScreenFragment

class MainScreenFriendProfileFragment:
    BaseMainScreenFragment<MainScreenFriendProfileViewModel>(R.layout.fragment_main_screen_current_friend){

    override val viewModel: MainScreenFriendProfileViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainScreenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
    }
}