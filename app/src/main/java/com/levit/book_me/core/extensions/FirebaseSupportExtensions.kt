package com.levit.book_me.core.extensions

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.utill.FirebaseConstants
import com.levit.book_me.network.models.google_books.GoogleBook

fun ProfileModel.getBaseInfoMap(): Map<String, String> = mapOf(
    FirebaseConstants.NAME_FIELD to name,
    FirebaseConstants.SURNAME_FIELD to surname,
    FirebaseConstants.WORDS_ABOUT_PERSON_FIELD to wordsAboutPerson,
    FirebaseConstants.PROFILE_PHOTO_URL_FIELD to profilePhotoUrl,
)

fun GoQuote.toMap(): Map<String, String> = mapOf(
    FirebaseConstants.QUOTE_TEXT_FIELD to text,
    FirebaseConstants.QUOTE_AUTHOR_FIELD to authorFullName,
    FirebaseConstants.QUOTE_TAG_FIELD to tag,
)

fun Author.toMap(): Map<String, String> = mapOf(
    FirebaseConstants.AUTHOR_NAME_FIELD to fullName,
)

fun GoogleBook.toMap(): Map<String, String> = mapOf(
    FirebaseConstants.GOOGLE_BOOK_TITLE_FIELD to (title ?: ""),
    FirebaseConstants.GOOGLE_BOOK_AUTHORS_FIELD to (listOfAuthors?.joinToString() ?: ""),
    FirebaseConstants.GOOGLE_BOOK_IMAGE_LINK_FIELD to (imageLinks?.getBiggestAvailableLink() ?: "")
)

fun Genre.toMap(): Map<String, String> = mapOf(
    FirebaseConstants.GENRE_TEXT_FIELD to string,
    FirebaseConstants.GENRE_IS_BIG_FIELD to isBig.toString(),
)