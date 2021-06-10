package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.Genre
import com.levit.book_me.interactors.interfaces.CreatingFavouriteGenresInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreatingFavouriteGenresInteractorImpl @Inject constructor(
    private val repository: GenresRepository,
): CreatingFavouriteGenresInteractor {

    override val genres: Flow<RetrofitResult<List<Genre>>>
        get() = repository.genres

    override suspend fun getGenres() {
        repository.getAllGenres()
    }
}