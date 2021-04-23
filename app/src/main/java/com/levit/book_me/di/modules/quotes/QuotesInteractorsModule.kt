package com.levit.book_me.di.modules.quotes

import com.levit.book_me.interactors.implementations.QuotesMainScreenInteractorImpl
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
import dagger.Module
import dagger.Provides

@Module
open class QuotesInteractorsModule {

    @Provides
    fun provideQuotesMainScreenInteractor(): QuotesMainScreenInteractor {
        return QuotesMainScreenInteractorImpl()
    }

}