package com.levit.book_me.core.di.modules

import android.content.Context
import androidx.room.Room
import com.levit.book_me.R
import com.levit.book_me.core.db.AppDatabase
import com.levit.book_me.core.db.appDatabaseMigrations
import com.levit.book_me.core.db.dao.BooksDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Context): AppDatabase {
        val destructiveMigrations =
            applicationContext.resources.getIntArray(R.array.destructive_migrations_allowed)
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .addMigrations(*appDatabaseMigrations)
            .fallbackToDestructiveMigrationFrom(*destructiveMigrations)
            .build()
    }

    @Provides
    @Singleton
    fun providesBooksDao(appDatabase: AppDatabase): BooksDao {
        return appDatabase.booksDao()
    }
}
