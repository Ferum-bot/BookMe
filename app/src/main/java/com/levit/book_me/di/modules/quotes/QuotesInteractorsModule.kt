package com.levit.book_me.di.modules.quotes

import com.levit.book_me.interactors.implementations.QuotesAuthorsScreenInteractorImpl
import com.levit.book_me.interactors.implementations.QuotesMainScreenInteractorImpl
import com.levit.book_me.interactors.implementations.QuotesScreenInteractorImpl
import com.levit.book_me.interactors.implementations.QuotesTagsScreenInteratorImpl
import com.levit.book_me.interactors.interfaces.QuotesAuthorsScreenInteractor
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
import com.levit.book_me.interactors.interfaces.QuotesScreenInteractor
import com.levit.book_me.interactors.interfaces.QuotesTagsScreenInterator
import com.levit.book_me.repositories.interfaces.GoQuotesAuthorRepository
import com.levit.book_me.repositories.interfaces.GoQuotesQuoteRepository
import com.levit.book_me.repositories.interfaces.GoQuotesTagRepository
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