package com.levit.book_me.di.modules.network

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
open class CoroutineContextModule {

    @Provides
    @Named("IODispatcherContext")
    fun provideIOCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    @Named("MainDispatcherContext")
    fun provideMainCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named("UnconfinedCoroutineContext")
    fun provideUnconfinedCoroutineContext(): CoroutineContext = Dispatchers.Unconfined
}