package com.levit.book_me.di.components

import com.levit.book_me.core_base.di.QuotesScreenScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.quotes.QuotesInteractorsModule
import com.levit.book_me.di.modules.quotes.QuotesViewModelsModule
import com.levit.book_me.ui.fragments.quotes.authors_screen.QuotesAuthorsScreenFragment
import com.levit.book_me.ui.fragments.quotes.main_screen.QuotesMainScreenFragment
import com.levit.book_me.ui.fragments.quotes.quotes_screen.QuotesScreenFragment
import com.levit.book_me.ui.fragments.quotes.tags_screen.QuotesTagsScreenFragment
import dagger.Subcomponent

@QuotesScreenScope
@Subcomponent(modules = [
    QuotesViewModelsModule::class,
    QuotesInteractorsModule::class,
])
interface QuotesComponent {

    fun inject(fragment: QuotesMainScreenFragment)

    fun inject(fragment: QuotesTagsScreenFragment)

    fun inject(fragment: QuotesAuthorsScreenFragment)

    fun inject(fragment: QuotesScreenFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): QuotesComponent
    }
}