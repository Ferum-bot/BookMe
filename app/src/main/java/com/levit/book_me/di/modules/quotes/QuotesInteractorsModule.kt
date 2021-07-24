package com.levit.book_me.di.modules.quotes

import com.levit.book_me.interactors.quotes.impl.QuotesAuthorsScreenInteractorImpl
import com.levit.book_me.interactors.quotes.impl.QuotesMainScreenInteractorImpl
import com.levit.book_me.interactors.quotes.impl.QuotesScreenInteractorImpl
import com.levit.book_me.interactors.quotes.impl.QuotesTagsScreenInteratorImpl
import com.levit.book_me.interactors.quotes.QuotesAuthorsScreenInteractor
import com.levit.book_me.interactors.quotes.QuotesMainScreenInteractor
import com.levit.book_me.interactors.quotes.QuotesScreenInteractor
import com.levit.book_me.interactors.quotes.QuotesTagsScreenInterator
import com.levit.book_me.repositories.guotes.QuotesRepository
import dagger.Module
import dagger.Provides

@Module
open class QuotesInteractorsModule {

    @Provides
    fun provideQuotesMainScreenInteractor(
        repository: QuotesRepository
    ): QuotesMainScreenInteractor {
        return QuotesMainScreenInteractorImpl(repository)
    }

    @Provides
    fun provideQuotesScreenInteractor(
        repository: QuotesRepository
    ): QuotesScreenInteractor {
        return QuotesScreenInteractorImpl(repository)
    }

    @Provides
    fun provideQuotesAuthorsScreenInterator(
        repository: QuotesRepository
    ): QuotesAuthorsScreenInteractor {
        return QuotesAuthorsScreenInteractorImpl(repository)
    }

    @Provides
    fun provideQuotesTagsScreenInteractor(
        repository: QuotesRepository
    ): QuotesTagsScreenInterator {
        return QuotesTagsScreenInteratorImpl(repository)
    }
}