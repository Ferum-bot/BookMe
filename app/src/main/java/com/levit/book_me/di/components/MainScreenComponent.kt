package com.levit.book_me.di.components

import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.DataSourceModule
import com.levit.book_me.di.modules.RepositoryModule
import com.levit.book_me.di.modules.firebase.FirebaseDataSourceModule
import com.levit.book_me.di.modules.firebase.FirebaseRepositoryModule
import com.levit.book_me.di.modules.main_screen.MainScreenInteractorsModule
import com.levit.book_me.di.modules.main_screen.MainScreenViewModelsModule
import com.levit.book_me.di.modules.network.NetworkModule
import com.levit.book_me.ui.activities.main_screen.MainScreenActivity
import com.levit.book_me.ui.fragments.main_screens.chats.MainScreenChatsFragment
import com.levit.book_me.ui.fragments.main_screens.current_chat.MainScreenCurrentChatFragment
import com.levit.book_me.ui.fragments.main_screens.current_friend_profile.MainScreenFriendProfileFragment
import com.levit.book_me.ui.fragments.main_screens.search_new_friend.MainScreenSearchFriendFragment
import com.levit.book_me.ui.fragments.main_screens.user_profile.MainScreenUserProfileFragment
import dagger.Subcomponent

@MainScreenScope
@Subcomponent(modules = [
    RepositoryModule::class,
    NetworkModule::class,
    DataSourceModule::class,
    FirebaseRepositoryModule::class,
    FirebaseDataSourceModule::class,
    MainScreenViewModelsModule::class,
    MainScreenInteractorsModule::class,
])
interface MainScreenComponent {

    fun inject(activity: MainScreenActivity)

    fun inject(fragment: MainScreenChatsFragment)

    fun inject(fragment: MainScreenUserProfileFragment)

    fun inject(fragment: MainScreenFriendProfileFragment)

    fun inject(fragment: MainScreenSearchFriendFragment)

    fun inject(fragment: MainScreenCurrentChatFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainScreenComponent
    }
}