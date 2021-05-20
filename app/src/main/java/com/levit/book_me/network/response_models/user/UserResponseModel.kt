package com.levit.book_me.network.response_models.user

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * This data class have temporary fields.
 * Lately I will refactored it.
 */
@Parcelize
data class UserResponseModel(

    @Json(name = "")
    val statusMessage: String,

    @Json(name = "")
    val statusCode: Int,

): Parcelable
