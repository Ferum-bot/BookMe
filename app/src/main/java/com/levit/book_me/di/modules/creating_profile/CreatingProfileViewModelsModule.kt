package com.levit.book_me.di.modules.creating_profile

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivityViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_books_want_to_read.CreatingBooksWantToReadViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors.CreatingFavouriteAuthorsViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books.CreatingFavouriteBooksViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres.CreatingFavouriteGenresViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_name_surname.CreatingNameSurnameViewModel
import com.levit.book_me.ui.fragments.creating_profile.creating_profile_image.CreatingProfileImageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreatingProfileViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreatingProfileActivityViewModel::class)
    abstract fun bindCreatingProfileActivityViewModel(viewModel: CreatingProfileActivityViewModel): ViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(CreatingFavouriteAuthorsViewModel::class)
    abstract fun bindCreatingFavouriteAuthorsViewModel(viewModel: CreatingFavouriteAuthorsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatingFavouriteBooksViewModel::class)
    abstract fun bindCreatingFavouriteBooksViewModel(viewModel: CreatingFavouriteBooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatingBooksWantToReadViewModel::class)
    abstract fun bindCreatingBooksWantToReadViewModel(viewModel: CreatingBooksWantToReadViewModel): ViewModel
}