package com.levit.book_me.repositories.profile.impl

import com.levit.book_me.core.models.Genre
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.profile.GenresRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class GenresRepositoryMock @Inject constructor(

    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,
): GenresRepository {

    companion object {

        private const val REPLAY_NUMBER = 1
        private const val EXTRA_CAPACITY = 0
    }

    private val _genres: MutableSharedFlow<RetrofitResult<List<Genre>>> = MutableSharedFlow(
        replay = REPLAY_NUMBER,
        extraBufferCapacity = EXTRA_CAPACITY,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val genres: SharedFlow<RetrofitResult<List<Genre>>> = _genres

    override suspend fun getAllGenres() =
    withContext(coroutineContext) {
        val genres = listOf<Genre>(
            Genre(string = "Classic"), Genre(string = "Science"), Genre(string = "Romance"), Genre(string = "Art"),
            Genre(string = "Business"), Genre(string = "Detective"), Genre(string = "Poetry"),

            Genre(string = "Drama", isBig = false),  Genre(string = "Cooking", isBig = false), Genre(string = "Religion", isBig = false),
            Genre(string = "Detective", isBig = false),  Genre(string = "Travel", isBig = false),  Genre(string = "Horror", isBig = false),
            Genre(string = "Health", isBig = false),  Genre(string = "Western", isBig = false),  Genre(string = "Dystopia", isBig = false),
            Genre(string = "Philosophy", isBig = false),
        )

        val result = RetrofitResult.Success.Value(genres)
        _genres.emit(result)
    }
}