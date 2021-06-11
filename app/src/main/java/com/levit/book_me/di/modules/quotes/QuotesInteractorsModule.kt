package com.levit.book_me.di.modules.quotes

import com.levit.book_me.interactors.quotes.impl.QuotesAuthorsScreenInteractorImpl
import com.levit.book_me.interactors.quotes.impl.QuotesMainScreenInteractorImpl
import com.levit.book_me.interactors.quotes.impl.QuotesScreenInteractorImpl
import com.levit.book_me.interactors.quotes.impl.QuotesTagsScreenInteratorImpl
import com.levit.book_me.interactors.quotes.QuotesAuthorsScreenInteractor
import com.levit.book_me.interactors.quotes.QuotesMainScreenInteractor
import com.levit.book_me.interactors.quotes.QuotesScreenInteractor
import com.levit.book_me.interactors.quotes.QuotesTagsScreenInterator
import com.levit.book_me.repositories.guotes.GoQuotesAuthorRepository
import com.levit.book_me.repositories.guotes.GoQuotesQuoteRepository
import com.levit.book_me.repositories.guotes.GoQuotesTagRepository
import dagger.Module
import dagger.Provides

@Module
open class QuotesInteractorsModule {

    @Provides
    fun provideQuotesMainScreenInteractor(
        tagsRepository: GoQuotesTagRepository,
        authorsRepository: GoQuotesAuthorRepository,
        quotesRepository: GoQuotesQuoteRepository
    ): QuotesMainScreenInteractor {
        return QuotesMainScreenInteractorImpl(tagsRepository, authorsRepository, quotesRepository)
    }

    @Provides
    fun provideQuotesScreenInteractor(
        repository: GoQuotesQuoteRepository,
    ): QuotesScreenInteractor {
        return QuotesScreenInteractorImpl(repository)
    }

    @Provides
    fun provideQuotesAuthorsScreenInterator(
        repository: GoQuotesAuthorRepository,
    ): QuotesAuthorsScreenInteractor {
        return QuotesAuthorsScreenInteractorImpl(repository)
    }

    @Provides
    fun provideQuotesTagsScreenInteractor(
        repository: GoQuotesTagRepository,
    ): QuotesTagsScreenInterator {
        return QuotesTagsScreenInteratorImpl(repository)
    }
}