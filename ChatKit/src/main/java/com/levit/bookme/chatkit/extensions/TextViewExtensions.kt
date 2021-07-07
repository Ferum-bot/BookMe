package com.levit.bookme.chatkit.extensions

import android.util.TypedValue
import android.widget.TextView

internal fun TextView.setTextSizeSp(sp: Int) {
    val unit = TypedValue.COMPLEX_UNIT_SP
    setTextSize(unit, sp.toFloat())
}