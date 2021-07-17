package com.levit.book_me.interactors.main_screen.impl

import android.net.Uri
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.profile.ProfileRepository
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@MainScreenScope
class UserProfileInteractorImpl @Inject constructor(
    private val repository: ProfileRepository
): UserProfileInteractor {

    override val profileModel: Flow<BaseRepositoryResult<ProfileModel>>
        = repository.resultProfile

    override val uploadPhotoResult: Flow<FirebaseStorageUploadResult>
        = repository.uploadToFirebaseStorageResult

    override suspend fun getProfile() {
        repository.getProfile()
    }

    override suspend fun updateName(newName: String) {
        repository.updateBaseInformation(
            name = newName,
        )
    }

    override suspend fun updateSurname(surname: String) {
        repository.updateBaseInformation(
            surname = surname
        )
    }

    override suspend fun updateWordsAboutPerson(wordsAboutPerson: String) {
        repository.updateBaseInformation(
            wordsAboutPerson = wordsAboutPerson
        )
    }

    override suspend fun updateQuote(quote: GoQuote) {
        repository.updateQuote(quote)
    }

    override suspend fun updateFavoriteAuthors(authors: List<Author>) {
        repository.updateFavoriteAuthors(authors)
    }

    override suspend fun updateFavoriteGenres(genres: List<Genre>) {
        repository.updateFavoriteGenres(genres)
    }

    override suspend fun updateFavoriteBooks(books: List<GoogleBook>) {
        repository.updateFavoriteBooks(books)
    }

    override suspend fun updateWantToReadBooks(books: List<GoogleBook>) {
        repository.updateWantToReadBooks(books)
    }

    override suspend fun uploadProfileImageToStorage(imageUri: Uri) {
        repository.upLoadUriToFirebaseStorage(imageUri)
    }

}