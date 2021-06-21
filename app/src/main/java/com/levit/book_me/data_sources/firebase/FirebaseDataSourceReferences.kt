package com.levit.book_me.data_sources.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseDataSourceReferences {

    private const val USERS = "users"
    private const val BASE_COLLECTIONS = "baseInformation"

    private const val FAVORITE_AUTHORS_DOCUMENT = "FavoriteAuthors"
    private const val FAVORITE_BOOKS_DOCUMENT = "FavoriteBooks"
    private const val WANT_TO_READ_BOOKS_DOCUMENT = "WantToReadBooks"
    private const val GENRES_DOCUMENT = "GENRES"
    private const val QUOTE_DOCUMENT = "Quote"

    private const val FAVORITE_AUTHOR_COLLECTION = "ChosenAuthors"
    private const val FAVORITE_BOOKS_COLLECTION = "ChosenBooks"
    private const val WANT_TO_READ_BOOKS_COLLECTION = "ChosenBooks"
    private const val GENRES_COLLECTION = "ChosenGenres"

    private val auth = Firebase.auth
    private val remote: FirebaseFirestore
    get() = Firebase.firestore

    private val userId = auth.currentUser?.uid ?: ""

    val userBaseInfoRef = remote.collection(USERS).document(userId)
    private val userCollectionsRef = userBaseInfoRef.collection(BASE_COLLECTIONS)

    val userFavoriteAuthorsCollectionRef = userCollectionsRef
        .document(FAVORITE_AUTHORS_DOCUMENT)
        .collection(FAVORITE_AUTHOR_COLLECTION)
    val userFavoriteBooksCollectionRef = userCollectionsRef
        .document(FAVORITE_BOOKS_DOCUMENT)
        .collection(FAVORITE_BOOKS_COLLECTION)
    val userWantToReadBooksCollectionRef = userCollectionsRef
        .document(WANT_TO_READ_BOOKS_DOCUMENT)
        .collection(WANT_TO_READ_BOOKS_COLLECTION)
    val userFavoriteGenresCollectionRef = userCollectionsRef
        .document(GENRES_DOCUMENT)
        .collection(GENRES_COLLECTION)

    val userQuoteDocument = userCollectionsRef
        .document(QUOTE_DOCUMENT)
}