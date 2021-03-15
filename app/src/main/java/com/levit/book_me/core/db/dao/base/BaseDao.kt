package com.levit.book_me.core.db.dao.base

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<T> {

    companion object {
        const val TRUE = 1
        const val FALSE = 0
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertWithResult(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBlocking(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertList(entities: Collection<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertListBlocking(entities: Collection<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateBlocking(entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateList(entities: Collection<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateListBlocking(entities: Collection<T>)
}
