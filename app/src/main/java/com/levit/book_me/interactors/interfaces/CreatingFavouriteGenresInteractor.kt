package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.Genre
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface CreatingFavouriteGenresInteractor {

    val genres: Flow<RetrofitResult<List<Genre>>>

    suspend fun getGenres()
}