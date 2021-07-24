package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class QuoteModel(

    @Json(name = "id")
    val id: Long = 0,

    @Json(name = "text")
    val text: String = "",

    @Json(name = "authorFullName")
    val authorFullName: String = "",

    @Json(name = "tag")
    val tag: String = "",
): Parcelable {

    /**
     * This property needed to correct work with
     * quotes in {QuotesAdapter.kt}
     */
    var isChosen: Boolean = false
}