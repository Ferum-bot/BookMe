package com.levit.book_me.di.components

import com.levit.book_me.di.modules.creating_profile.CreatingProfileViewModelsModule
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.subcomponents.CreatingProfileSubComponents
import com.levit.book_me.di.modules.DataSourceModule
import com.levit.book_me.di.modules.RepositoryModule
import com.levit.book_me.di.modules.creating_profile.CreatingProfileInteractorsModule
import com.levit.book_me.di.modules.firebase.FirebaseDataSourceModule
import com.levit.book_me.di.modules.firebase.FirebaseRepositoryModule
import com.levit.book_me.di.modules.network.NetworkModule
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.fragments.creating_profile.creating_books_want_to_read.CreatingBooksWantToReadFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors.CreatingFavouriteAuthorsFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books.CreatingFavouriteBooksFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres.CreatingFavouriteGenresFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_name_surname.CreatingNameSurnameFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_profile_image.CreatingProfileImageFragment
import com.levit.book_me.ui.fragments.creating_profile.search_books.SearchBooksFragment
import com.levit.book_me.ui.fragments.creating_profile.search_favourites_authors.SearchFavouriteAuthorsFragment
import com.levit.book_me.ui.fragments.quotes.authors_screen.QuotesAuthorsScreenFragment
import com.levit.book_me.ui.fragments.quotes.main_screen.QuotesMainScreenFragment
import com.levit.book_me.ui.fragments.quotes.quotes_screen.QuotesScreenFragment
import com.levit.book_me.ui.fragments.quotes.tags_screen.QuotesTagsScreenFragment
import dagger.Subcomponent

@CreatingProfileScope
@Subcomponent(modules = [
    CreatingProfileViewModelsModule::class,
    CreatingProfileInteractorsModule::class,
    FirebaseDataSourceModule::class,
    FirebaseRepositoryModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    DataSourceModule::class,
    CreatingProfileSubComponents::class,
])
interface CreatingProfileComponent {

    fun inject(activity: CreatingProfileActivity)

    fun inject(fragment: CreatingNameSurnameFragment)

    fun inject(fragment: CreatingProfileImageFragment)

    fun inject(fragment: CreatingFavouriteGenresFragment)

    fun inject(fragment: CreatingFavouriteAuthorsFragment)

    fun inject(fragment: SearchFavouriteAuthorsFragment)

    fun inject(fragment: CreatingFavouriteBooksFragment)

    fun inject(fragment: CreatingBooksWantToReadFragment)

    fun inject(fragment: SearchBooksFragment)

    fun inject(fragment: QuotesMainScreenFragment)

    fun inject(fragment: QuotesAuthorsScreenFragment)

    fun inject(fragment: QuotesTagsScreenFragment)

    fun inject(fragment: QuotesScreenFragment)

    fun viewModelFactory(): ViewModelFactory

    fun searchFavouriteAuthorsComponent(): SearchFavouriteAuthorsComponent.Builder
    fun searchBooksComponent(): SearchBooksComponent.Builder
    fun quotesComponent(): QuotesComponent.Builder

    @Subcomponent.Builder
    interface Builder {

        fun build(): CreatingProfileComponent
    }
}