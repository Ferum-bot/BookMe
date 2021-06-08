package com.levit.book_me.core.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(

    @Json(name = "")
    val uuid: String,

    @Json(name = "")
    val profileModel: ProfileModel,

): Parcelable
