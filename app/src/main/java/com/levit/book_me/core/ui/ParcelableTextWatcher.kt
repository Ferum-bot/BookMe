package com.levit.book_me.core.ui

import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.parcel.Parcelize

@Parcelize
class ParcelableTextWatcher: TextWatcher, Parcelable {

    var onTextChangeListener: ((CharSequence, Int, Int, Int) -> Unit)? = null
    var onAfterTextChanged: ((Editable) -> Unit)? = null
    var onBeforeTextChanged: ((CharSequence, Int, Int, Int) -> Unit)? = null

    override fun afterTextChanged(s: Editable) {
        onAfterTextChanged?.invoke(s)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        onBeforeTextChanged?.invoke(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChangeListener?.invoke(s, start, before, count)
    }
}