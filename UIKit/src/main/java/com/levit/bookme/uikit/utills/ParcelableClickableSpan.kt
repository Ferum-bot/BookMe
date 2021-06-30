package com.levit.bookme.uikit.utills

import android.os.Parcelable
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import kotlinx.android.parcel.Parcelize

@Parcelize
class ParcelableClickableSpan: ClickableSpan(), Parcelable {

    var onTextClicked: ((View) -> Unit)? = null
    var onDrawStateUpdate: ((TextPaint) -> Unit)? = null

    override fun onClick(widget: View) {
        onTextClicked?.invoke(widget)
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        onDrawStateUpdate?.invoke(ds)
    }
}