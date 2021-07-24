package com.levit.book_me.core.models.profile

import android.os.Parcelable
import com.levit.book_me.core.models.profile.ProfileModel
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModel(

    @Json(name = "")
    val uuid: String,

    @Json(name = "")
    val profileModel: ProfileModel,

    ): Parcelable
