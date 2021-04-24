package com.levit.book_me.di.modules.quotes

import com.levit.book_me.interactors.implementations.QuotesMainScreenInteractorImpl
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
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

}