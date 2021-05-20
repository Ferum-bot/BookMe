package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoQuote(
    @Json(name = "text")
    val text: String,

    @Json(name = "author")
    val authorFullName: String,

    @Json(name = "tag")
    val tag: String,
): Parcelable {

    /**
     * This property needed to correct work with
     * quotes in {QuotesAdapter.kt}
     */
    var isChosen: Boolean = false
}