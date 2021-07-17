package com.levit.book_me.core.models.profile

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FriendProfileModel(

    @Json(name = "")
    val id: Long = 0,

    @Json(name = "")
    val launchTimeUTC0: String = "02.06.2021 16:32:51",

    @Json(name = "")
    val model: ProfileModel = ProfileModel()

): Parcelable
