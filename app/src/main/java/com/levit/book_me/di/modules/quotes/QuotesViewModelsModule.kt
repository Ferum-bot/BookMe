package com.levit.book_me.di.modules.quotes

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.fragments.quotes.authors_screen.QuotesAuthorsScreenViewModel
import com.levit.book_me.ui.fragments.quotes.main_screen.QuotesMainScreenViewModel
import com.levit.book_me.ui.fragments.quotes.quotes_screen.QuotesScreenViewModel
import com.levit.book_me.ui.fragments.quotes.tags_screen.QuotesTagsScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class QuotesViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuotesMainScreenViewModel::class)
    abstract fun bindQuotesMainScreenViewModel(viewModel: QuotesMainScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuotesTagsScreenViewModel::class)
    abstract fun bindQuotesTagsScreenViewModel(viewModel: QuotesTagsScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuotesAuthorsScreenViewModel::class)
    abstract fun bindQuotesAuthorsScreenViewModel(viewModel: QuotesAuthorsScreenViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(QuotesScreenViewModel::class)
    abstract fun bindQuotesScreenViewModel(viewModel: QuotesScreenViewModel): ViewModel

}