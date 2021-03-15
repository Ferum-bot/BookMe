package com.levit.book_me.core.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.levit.book_me.core.db.converters.list.AuthorIdsListConverter
import com.levit.book_me.core.db.dao.BooksDao

@TypeConverters(AuthorIdsListConverter::class)
@Entity(tableName = BooksDao.TABLE_NAME)
data class BookDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "cover")
    val cover: String,
    @ColumnInfo(name = "author_ids")
    val authorIds: List<Long>
)
