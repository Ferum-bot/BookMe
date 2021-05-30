package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.Genre
import com.levit.book_me.interactors.interfaces.CreatingFavouriteGenresInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreatingFavouriteGenresInteractorImpl @Inject constructor(

): CreatingFavouriteGenresInteractor {

    companion object {
        private const val REPEAT_VALUE = 15
    }

    override val genres: Flow<RetrofitResult<List<Genre>>>
        get() = flow {
            val resultList = mutableListOf<Genre>()

            repeat(REPEAT_VALUE) {
                val genre = Genre(
                    id = 0,
                    string = "Genre $it",
                    isBig = it % 2 == 0,
                )
                resultList.add(genre)
            }
            val result = RetrofitResult.Success.Value(resultList)
            emit(result)
        }

    override suspend fun getGenres() {

    }
}