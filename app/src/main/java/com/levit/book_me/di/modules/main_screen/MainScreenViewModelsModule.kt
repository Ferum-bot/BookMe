package com.levit.book_me.di.modules.main_screen

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.activities.main_screen.MainScreenActivityViewModel
import com.levit.book_me.ui.fragments.main_screens.chats.MainScreenChatsViewModel
import com.levit.book_me.ui.fragments.main_screens.current_chat.MainScreenCurrentChatViewModel
import com.levit.book_me.ui.fragments.main_screens.current_friend_profile.MainScreenFriendProfileViewModel
import com.levit.book_me.ui.fragments.main_screens.user_profile.MainScreenUserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainScreenViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenActivityViewModel::class)
    abstract fun bindMainScreenActivityViewModel(viewModel: MainScreenActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenChatsViewModel::class)
    abstract fun bindMainScreenChatsViewModel(viewModel: MainScreenChatsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenUserProfileViewModel::class)
    abstract fun bindMainScreenUserProfileViewModel(viewModel: MainScreenUserProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenFriendProfileViewModel::class)
    abstract fun bindMainScreenCurrentFriendProfileViewModel(viewModel: MainScreenFriendProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenCurrentChatViewModel::class)
    abstract fun bindMainScreenCurrentChatViewModel(viewModel: MainScreenCurrentChatViewModel): ViewModel
}