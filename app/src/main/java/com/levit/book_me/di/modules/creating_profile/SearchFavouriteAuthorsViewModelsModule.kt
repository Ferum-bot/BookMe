package com.levit.book_me.di.modules.creating_profile

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors.SearchFavouriteAuthorsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchFavouriteAuthorsViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchFavouriteAuthorsViewModel::class)
    abstract fun bindSearchFavouriteAuthorsViewModel(viewModel: SearchFavouriteAuthorsViewModel): ViewModel

}