package com.levit.book_me.ui.fragments.main_screens.chats

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.ui.base.BaseMainScreenFragment

class MainScreenChatsFragment:
    BaseMainScreenFragment<MainScreenChatsViewModel>(R.layout.fragment_main_screen_chats) {

    override val viewModel: MainScreenChatsViewModel by viewModels {
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