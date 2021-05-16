package com.levit.book_me.di.components

import com.levit.book_me.core_base.di.SearchFavouriteAuthorsScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.DataSourceModule
import com.levit.book_me.di.modules.RepositoryModule
import com.levit.book_me.di.modules.creating_profile.SearchFavouriteAuthorsInteractorsModule
import com.levit.book_me.di.modules.creating_profile.SearchFavouriteAuthorsViewModelsModule
import com.levit.book_me.di.modules.network.NetworkModule
import com.levit.book_me.ui.fragments.creating_profile.search_favourites_authors.SearchFavouriteAuthorsFragment
import dagger.Subcomponent

@SearchFavouriteAuthorsScope
@Subcomponent(modules = [
    SearchFavouriteAuthorsViewModelsModule::class,
    SearchFavouriteAuthorsInteractorsModule::class,
])
interface SearchFavouriteAuthorsComponent {

    fun inject(fragment: SearchFavouriteAuthorsFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): SearchFavouriteAuthorsComponent
    }
}