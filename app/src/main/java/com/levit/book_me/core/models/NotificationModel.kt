package com.levit.book_me.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NotificationModel(

    val id: Int,

    val title: String,

    val text: String,


): Parcelable
