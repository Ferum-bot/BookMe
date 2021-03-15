package com.levit.book_me.core.db.dao

import androidx.room.Dao
import com.levit.book_me.core.db.dao.base.BaseDao
import com.levit.book_me.core.db.entities.BookDB

@Dao
abstract class BooksDao: BaseDao<BookDB>() {

    companion object {
        const val TABLE_NAME = "books"
    }
}
