package com.levit.book_me.interactors.main_screen.impl

import android.net.Uri
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TestUserProfileInteractor @Inject constructor(
    private val dataSource: CacheProfileDataSource,
    private val uploadPhotoStorage: FirebaseStorageUploadUriRepository,
): UserProfileInteractor {

    override val profileModel: Flow<BaseRepositoryResult<ProfileModel>> = dataSource.profile.map { model ->
        BaseRepositoryResult.CacheResult(model)
    }

    override val uploadPhotoResult: Flow<FirebaseStorageUploadResult> = uploadPhotoStorage.uploadToFirebaseStorageResult

    override suspend fun getProfile() {
        dataSource.getProfile()
    }

    override suspend fun updateName(newName: String) {

    }

    override suspend fun updateSurname(surname: String) {

    }

    override suspend fun updateWordsAboutPerson(wordsAboutPerson: String) {

    }

    override suspend fun updateQuote(quote: QuoteModel) {

    }

    override suspend fun updateFavoriteAuthors(authors: List<Author>) {

    }

    override suspend fun updateFavoriteGenres(genres: List<Genre>) {

    }

    override suspend fun updateFavoriteBooks(books: List<GoogleBook>) {

    }

    override suspend fun updateWantToReadBooks(books: List<GoogleBook>) {

    }

    override suspend fun uploadProfileImageToStorage(imageUri: Uri) {
        uploadPhotoStorage.upLoadUriToFirebaseStorage(imageUri)
    }
}