package com.levit.book_me.di.components

import com.levit.book_me.core_base.di.SearchBooksScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.DataSourceModule
import com.levit.book_me.di.modules.RepositoryModule
import com.levit.book_me.di.modules.creating_profile.SearchBooksInteractorModule
import com.levit.book_me.di.modules.creating_profile.SearchBooksViewModelModule
import com.levit.book_me.di.modules.network.NetworkModule
import com.levit.book_me.ui.fragments.creating_profile.search_books.SearchBooksFragment
import dagger.Subcomponent

@SearchBooksScope
@Subcomponent(modules = [
    SearchBooksViewModelModule::class
])
interface SearchBooksComponent {

    fun inject(fragment: SearchBooksFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): SearchBooksComponent
    }

}