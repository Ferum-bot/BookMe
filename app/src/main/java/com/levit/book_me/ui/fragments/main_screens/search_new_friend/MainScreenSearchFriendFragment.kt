package com.levit.book_me.ui.fragments.main_screens.search_new_friend

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.ui.base.BaseMainScreenFragment

class MainScreenSearchFriendFragment
    : BaseMainScreenFragment<MainScreenSearchFriendViewModel>(R.layout.fragment_main_screen_searching_new_friend){

    override val viewModel: MainScreenSearchFriendViewModel by viewModels {
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