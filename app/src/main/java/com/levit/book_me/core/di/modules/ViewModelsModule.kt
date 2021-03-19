package com.levit.book_me.core.di.modules

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.fragments.authorization.choose_type_authorization.ChooseTypeAuthorizationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChooseTypeAuthorizationViewModel::class)
    abstract fun bindChooseTypeAuthorizationViewModel(viewModel: ChooseTypeAuthorizationViewModel): ViewModel
}