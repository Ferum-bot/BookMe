package com.levit.book_me.core.di.modules

import android.content.Context
import com.levit.book_me.core.repositories.FirstLaunchRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideFirstLaunchRepository(context: Context): FirstLaunchRepository {
        return FirstLaunchRepository(context)
    }
}