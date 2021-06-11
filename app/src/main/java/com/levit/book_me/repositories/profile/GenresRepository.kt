package com.levit.book_me.repositories.profile

import com.levit.book_me.core.models.Genre
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface GenresRepository {

    val genres: SharedFlow<RetrofitResult<List<Genre>>>

    suspend fun getAllGenres()

}