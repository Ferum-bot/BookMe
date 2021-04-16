package com.levit.book_me.di.components

import com.levit.book_me.di.modules.creating_profile.CreatingProfileViewModelsModule
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.creating_profile.CreatingProfileInteractorsModule
import com.levit.book_me.di.modules.creating_profile.CreatingProfileRepositoryModule
import com.levit.book_me.di.modules.firebase.FirebaseDataSourceModule
import com.levit.book_me.di.modules.firebase.FirebaseRepositoryModule
import com.levit.book_me.di.modules.network.NetworkModule
import com.levit.book_me.di.modules.network.InterceptorsModule
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors.CreatingFavouriteAuthorsFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books.CreatingFavouriteBooksFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres.CreatingFavouriteGenresFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_name_surname.CreatingNameSurnameFragment
import com.levit.book_me.ui.fragments.creating_profile.creating_profile_image.CreatingProfileImageFragment
import com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors.SearchFavouriteAuthorsFragment
import dagger.Subcomponent

@CreatingProfileScope
@Subcomponent(modules = [
    CreatingProfileViewModelsModule::class,
    CreatingProfileInteractorsModule::class,
    CreatingProfileRepositoryModule::class,
    FirebaseDataSourceModule::class,
    FirebaseRepositoryModule::class,
    NetworkModule::class,
])
interface CreatingProfileComponent {

    fun inject(activity: CreatingProfileActivity)

    fun inject(fragment: CreatingNameSurnameFragment)

    fun inject(fragment: CreatingProfileImageFragment)

    fun inject(fragment: CreatingFavouriteGenresFragment)

    fun inject(fragment: CreatingFavouriteAuthorsFragment)

    fun inject(fragment: SearchFavouriteAuthorsFragment)

    fun inject(fragment: CreatingFavouriteBooksFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): CreatingProfileComponent
    }
}