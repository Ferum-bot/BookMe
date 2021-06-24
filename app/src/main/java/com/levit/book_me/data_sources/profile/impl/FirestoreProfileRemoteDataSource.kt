package com.levit.book_me.data_sources.profile.impl

import com.google.firebase.firestore.QueryDocumentSnapshot
import com.levit.book_me.core.extensions.toMap
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.utill.FirebaseConstants
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.models.google_books.ImageLinks
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * I know that this is awful code, but firebase does not have
 * comfortable API to connect with, so lately we will migrate to
 * own backend server.
 */
class FirestoreProfileRemoteDataSource @Inject constructor(

): BaseProfileRemoteDataSource {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 0
    }

    private val _remoteProfile: MutableSharedFlow<RetrofitResult<ProfileModel>> = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val remoteProfile: SharedFlow<RetrofitResult<ProfileModel>> = _remoteProfile

    private var isErrorGet = false

    private var fieldsCount = 0

    private var name: String = ""
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var surname: String = ""
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var profilePhotoUrl: String = ""
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var wordsAboutPerson: String = ""
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var favoriteGenres: List<Genre> = listOf()
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var favoriteAuthors: List<Author> = listOf()
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var favoriteBooks: List<GoogleBook> = listOf()
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var wantToReadBooks: List<GoogleBook> = listOf()
    set(value) {
        field = value
        handleFieldSetter()
    }

    private var quote: GoQuote = GoQuote()
    set(value) {
        field = value
        handleFieldSetter()
    }

    override suspend fun getProfile() {
        isErrorGet = false
        fieldsCount = 0

        getName()
        getSurname()
        getWordsAboutPerson()
        getProfilePhotoUrl()
        getFavoriteGenres()
        getFavoriteAuthors()
        getFavoriteBooks()
        getWantToReadBooks()
        getQuote()
    }

    override suspend fun deleteProfile() {
        val exception = Exception("Not supported yet")
        _remoteProfile.emit(RetrofitResult.Failure.Error(exception))
    }

    override suspend fun updateBaseInformation(name: String?, surname: String?, wordsAboutPerson: String?) {
        isErrorGet = false

        if (name != null) {
            updateName(name)
        }
        if (surname != null) {
            updateSurname(surname)
        }
        if (wordsAboutPerson != null) {
            updateWordsAboutPerson(wordsAboutPerson)
        }
    }

    override suspend fun updateFavoriteAuthors(authors: List<Author>) {
        isErrorGet = false

        val ref = FirebaseDataSourceReferences.userFavoriteAuthorsCollectionRef
        authors.forEachIndexed { index, author ->
            ref.document(FirebaseConstants.getAuthorDocumentPath(index))
                .set(author.toMap())
                .addOnFailureListener { onFailureListener(it) }
                .addOnSuccessListener {
                    if (isErrorGet) {
                        return@addOnSuccessListener
                    }
                    if (index == authors.size - 1) {
                        fieldUpdated()
                    }
                }
        }
    }

    override suspend fun updateFavoriteGenres(genres: List<Genre>) {
        isErrorGet = false

        val ref = FirebaseDataSourceReferences.userFavoriteGenresCollectionRef
        genres.forEachIndexed { index, genre ->
            ref.document(FirebaseConstants.getGenreDocumentPath(index))
                .set(genre.toMap())
                .addOnFailureListener { onFailureListener(it) }
                .addOnSuccessListener {
                    if (isErrorGet) {
                        return@addOnSuccessListener
                    }
                    if (index == genres.size - 1) {
                        fieldUpdated()
                    }
                }
        }
    }

    override suspend fun updateQuote(quote: GoQuote) {
        isErrorGet = false

        val ref = FirebaseDataSourceReferences.userQuoteDocument
        ref.set(quote.toMap())
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener {
                if (isErrorGet) {
                    return@addOnSuccessListener
                }
                fieldUpdated()
            }
    }

    override suspend fun updateFavoriteBooks(books: List<GoogleBook>) {
        isErrorGet = false

        val ref = FirebaseDataSourceReferences.userFavoriteBooksCollectionRef
        books.forEachIndexed { index, book ->
            ref.document(FirebaseConstants.getBookDocumentPath(index))
                .set(book.toMap())
                .addOnFailureListener { onFailureListener(it) }
                .addOnSuccessListener {
                    if (isErrorGet) {
                        return@addOnSuccessListener
                    }
                    if (index == books.size - 1) {
                        fieldUpdated()
                    }
                }
        }
    }

    override suspend fun updateWantToReadBooks(books: List<GoogleBook>) {
        isErrorGet = false

        val ref = FirebaseDataSourceReferences.userWantToReadBooksCollectionRef
        books.forEachIndexed { index, book ->
            ref.document(FirebaseConstants.getBookDocumentPath(index))
                .set(book.toMap())
                .addOnFailureListener { onFailureListener(it) }
                .addOnSuccessListener {
                    if (isErrorGet) {
                        return@addOnSuccessListener
                    }
                    if (index == books.size - 1) {
                        fieldUpdated()
                    }
                }
        }
    }

    private fun onFailureListener(exception: Exception) = runBlocking {
        if (isErrorGet) {
            return@runBlocking
        }
        isErrorGet = true

        val result = RetrofitResult.Failure.Error(exception)
        _remoteProfile.emit(result)
    }

    private fun updateName(name: String) {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.update("name", name)
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { fieldUpdated() }
    }

    private fun getName() {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { document ->
                val name = document.get(FirebaseConstants.NAME_FIELD) as? String
                this.name = name ?: ""
            }
    }

    private fun updateSurname(surname: String) {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.update("surname", surname)
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { fieldUpdated() }
    }

    private fun getSurname() {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { document ->
                val surname = document.get(FirebaseConstants.SURNAME_FIELD) as? String
                this.surname = surname ?: ""
            }
    }

    private fun getProfilePhotoUrl() {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { document ->
                val url = document.get(FirebaseConstants.PROFILE_PHOTO_URL_FIELD) as? String
                this.profilePhotoUrl = url ?: ""
            }
    }

    private suspend fun updateWordsAboutPerson(wordsAboutPerson: String) {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.update("wordsAboutPerson", wordsAboutPerson)
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { fieldUpdated() }
    }

    private suspend fun getWordsAboutPerson() {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { document ->
                val words = document.get(FirebaseConstants.WORDS_ABOUT_PERSON_FIELD) as? String
                this.wordsAboutPerson = words ?: ""
            }
    }

    private fun getFavoriteGenres() {
        val ref = FirebaseDataSourceReferences.userFavoriteGenresCollectionRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { collection ->
                val genres = mutableListOf<Genre>()
                for (document in collection) {
                    val genre = document.getGenre()
                    genres.add(genre)
                }
                this.favoriteGenres = genres
            }
    }

    private fun getFavoriteAuthors() {
        val ref = FirebaseDataSourceReferences.userFavoriteAuthorsCollectionRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { collection ->
                val authors = mutableListOf<Author>()
                for (document in collection) {
                    val author = document.getAuthor()
                    authors.add(author)
                }
                this.favoriteAuthors = authors
            }
    }

    private fun getFavoriteBooks() {
        val ref = FirebaseDataSourceReferences.userFavoriteBooksCollectionRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { collection ->
                val books = mutableListOf<GoogleBook>()
                for (document in collection) {
                    val book = document.getGoogleBook()
                    books.add(book)
                }
                this.favoriteBooks = books
            }
    }

    private fun getWantToReadBooks() {
        val ref = FirebaseDataSourceReferences.userWantToReadBooksCollectionRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { collection ->
                val books = mutableListOf<GoogleBook>()
                for (document in collection) {
                    val book = document.getGoogleBook()
                    books.add(book)
                }
                this.wantToReadBooks = books
            }
    }

    private fun getQuote() {
        val ref = FirebaseDataSourceReferences.userQuoteDocument
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { document ->
                val text = document.get(FirebaseConstants.QUOTE_TEXT_FIELD) as? String
                val author = document.get(FirebaseConstants.QUOTE_AUTHOR_FIELD) as? String
                val tag = document.get(FirebaseConstants.QUOTE_TAG_FIELD) as? String

                val quote = GoQuote(
                    text = text ?: "",
                    authorFullName = author ?: "",
                    tag = tag ?: ""
                )
                this.quote = quote
            }
    }

    private fun fieldUpdated() = runBlocking {
        getProfile()
    }

    private fun handleFieldSetter() {
        fieldsCount++
        if (fieldsCount == 9) {
            runBlocking {  createProfile() }
            fieldsCount = 0
        }
    }

    private suspend fun createProfile() {
        val profile = ProfileModel(
            name = name,
            surname = surname,
            wordsAboutPerson = wordsAboutPerson,
            profilePhotoUrl = profilePhotoUrl,
            favouriteGenres = favoriteGenres,
            favouriteAuthors = favoriteAuthors,
            favouriteBooks = favoriteBooks,
            wantToReadBooks = wantToReadBooks,
            quote = quote,
        )
        val result = RetrofitResult.Success.Value(profile)

        _remoteProfile.emit(result)
    }

    private fun QueryDocumentSnapshot.getGenre(): Genre {
        val text = get(FirebaseConstants.GENRE_TEXT_FIELD) as? String
        val isBig = get(FirebaseConstants.GENRE_IS_BIG_FIELD) as? String
        return Genre(
            string = text ?: "",
            isBig = isBig.toBoolean(),
        )
    }

    private fun QueryDocumentSnapshot.getAuthor(): Author {
        val name = get(FirebaseConstants.AUTHOR_NAME_FIELD) as? String
        return Author(name ?: "")
    }

    private fun QueryDocumentSnapshot.getGoogleBook(): GoogleBook {
        val title = get(FirebaseConstants.GOOGLE_BOOK_TITLE_FIELD) as? String
        val authors = get(FirebaseConstants.GOOGLE_BOOK_AUTHORS_FIELD) as? String
        val listOfAuthors = authors
            ?.split(", ")
            ?: emptyList()

        val imageLink = get(FirebaseConstants.GOOGLE_BOOK_IMAGE_LINK_FIELD) as? String
        val imageLinks = ImageLinks(imageLink)
        return GoogleBook(
            title = title,
            listOfAuthors = listOfAuthors,
            imageLinks = imageLinks,
        )
    }
}