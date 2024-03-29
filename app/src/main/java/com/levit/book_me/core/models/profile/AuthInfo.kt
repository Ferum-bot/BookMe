package com.levit.book_me.core.models.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class AuthInfo(

    val userId: String,

    val fcmPushToken: String,

): Parcelable
