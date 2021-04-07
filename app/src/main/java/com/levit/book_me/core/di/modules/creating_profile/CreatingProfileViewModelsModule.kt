package com.levit.book_me.core.di.modules.creating_profile

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres.CreatingFavouriteGenresViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_name_surname.CreatingNameSurnameFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_name_surname.CreatingNameSurnameViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_profile_image.CreatingProfileImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreatingProfileViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreatingNameSurnameViewModel::class)
    abstract fun bindCreatingNameSurnameViewModel(viewModel: CreatingNameSurnameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatingProfileImageViewModel::class)
    abstract fun bindCreatingProfileImageViewModel(viewModel: CreatingProfileImageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatingFavouriteGenresViewModel::class)
    abstract fun bindCreatingFavouriteGenresViewModel(viewModel: CreatingFavouriteGenresViewModel): ViewModel
}