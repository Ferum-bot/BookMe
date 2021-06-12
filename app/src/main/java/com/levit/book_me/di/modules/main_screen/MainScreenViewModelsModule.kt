package com.levit.book_me.di.modules.main_screen

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.activities.main_screen.MainScreenActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainScreenViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenActivityViewModel::class)
    abstract fun bindMainScreenActivityViewModel(viewModel: MainScreenActivityViewModel): ViewModel
}