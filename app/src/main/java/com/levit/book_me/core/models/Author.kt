package com.levit.book_me.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Author(
    val fullName: String
): Parcelable