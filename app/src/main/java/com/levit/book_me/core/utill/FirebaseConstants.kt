package com.levit.book_me.core.utill

object FirebaseConstants {

    const val NAME_FIELD = "name"
    const val SURNAME_FIELD = "surname"
    const val PROFILE_PHOTO_URL_FIELD = "profileImageUrl"
    const val WORDS_ABOUT_PERSON_FIELD = "wordsAboutPerson"

    const val QUOTE_TEXT_FIELD = "text"
    const val QUOTE_AUTHOR_FIELD = "authorFullName"
    const val QUOTE_TAG_FIELD = "tag"

    const val AUTHOR_NAME_FIELD = "fullName"

    const val GOOGLE_BOOK_TITLE_FIELD = "title"
    const val GOOGLE_BOOK_AUTHORS_FIELD = "authors"
    const val GOOGLE_BOOK_IMAGE_LINK_FIELD = "imageLink"

    const val GENRE_TEXT_FIELD = "string"
    const val GENRE_IS_BIG_FIELD = "isBig"

    const val AUTHOR_PREF_NAME = "author"
    const val BOOK_PREF_NAME = "book"
    const val GENRE_PREF_NAME = "genre"

    fun getAuthorDocumentPath(index: Int) = "$AUTHOR_PREF_NAME$index"
    fun getBookDocumentPath(index: Int) = "$BOOK_PREF_NAME$index"
    fun getGenreDocumentPath(index: Int) = "$GENRE_PREF_NAME$index"
}