package com.levit.book_me.di.modules.network

import com.levit.book_me.di.DIConstants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
open class CoroutineContextModule {

    @Provides
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    fun provideIOCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    @Named(DIConstants.MAIN_DISPATCHER_CONTEXT)
    fun provideMainCoroutineContext(): CoroutineContext = Dispatchers.Main

    @Provides
    @Named(DIConstants.UNCONFINED_DISPATCHER_CONTEXT)
    fun provideUnconfinedCoroutineContext(): CoroutineContext = Dispatchers.Unconfined

    @Provides
    @Named(DIConstants.DEFAULT_DISPATCHER_CONTEXT)
    fun provideDefaultCoroutineContext(): CoroutineContext = Dispatchers.Default
}