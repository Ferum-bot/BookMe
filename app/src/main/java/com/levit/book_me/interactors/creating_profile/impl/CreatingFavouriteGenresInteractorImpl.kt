package com.levit.book_me.interactors.creating_profile.impl

import com.levit.book_me.core.models.Genre
import com.levit.book_me.interactors.creating_profile.CreatingFavouriteGenresInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.profile.GenresRepository
import kotlinx.coroutines.flow.Flow
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