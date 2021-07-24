package com.levit.book_me.data_sources.profile.impl

import android.net.Uri
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import com.levit.book_me.data_sources.profile.ProfileRemoteDataSourceFacade
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class FirestoreDataSourceFacade @Inject constructor(
    private val baseDataSource: BaseProfileRemoteDataSource,
    private val profilePhotoDataSource: FirebaseStorageUploadUriDataSource
): ProfileRemoteDataSourceFacade {

    override val uploadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>
    = profilePhotoDataSource.loadToFirebaseStorageResult

    override val remoteProfile: SharedFlow<RetrofitResult<ProfileModel>>
    = baseDataSource.remoteProfile

    override suspend fun upLoadUriToFirebaseStorage(uri: Uri) {
        val ref = FirebaseStorageReferences.getStorageProfileImageReference()
        profilePhotoDataSource.loadUriToFirebaseStorage(uri, ref)
    }

    override suspend fun getProfile() {
        baseDataSource.getProfile()
    }

    override suspend fun deleteProfile() {
        baseDataSource.deleteProfile()
    }

    override suspend fun updateBaseInformation(name: String?, surname: String?, wordsAboutPerson: String?) {
        baseDataSource.updateBaseInformation(name, surname, wordsAboutPerson)
    }

    override suspend fun updateQuote(quote: QuoteModel) {
        baseDataSource.updateQuote(quote)
    }

    override suspend fun updateFavoriteAuthors(authors: List<Author>) {
        baseDataSource.updateFavoriteAuthors(authors)
    }

    override suspend fun updateFavoriteGenres(genres: List<Genre>) {
        baseDataSource.updateFavoriteGenres(genres)
    }

    override suspend fun updateFavoriteBooks(books: List<GoogleBook>) {
        baseDataSource.updateFavoriteBooks(books)
    }

    override suspend fun updateWantToReadBooks(books: List<GoogleBook>) {
        baseDataSource.updateWantToReadBooks(books)
    }


}