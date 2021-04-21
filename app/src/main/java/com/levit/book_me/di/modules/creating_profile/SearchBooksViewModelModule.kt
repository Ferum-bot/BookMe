package com.levit.book_me.di.modules.creating_profile

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.fragments.creating_profile.search_books.SearchBooksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchBooksViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchBooksViewModel::class)
    abstract fun bindSearchBooksViewModel(viewModel: SearchBooksViewModel): ViewModel
}