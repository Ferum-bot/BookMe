package com.levit.book_me.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.levit.book_me.core.db.AppDatabase.Companion.DATABASE_VERSION
import com.levit.book_me.core.db.dao.BooksDao
import com.levit.book_me.core.db.entities.BookDB

@Database(
    entities = [
        BookDB::class
    ],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "BookMeDatabase.db"
    }

    abstract fun booksDao(): BooksDao
}
