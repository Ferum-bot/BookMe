package com.levit.book_me.roundcloudsview.core.extensions

import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View

internal fun View.getLeftPadding(): Int {
    if (paddingStart == 0) {
        return paddingLeft
    }
    return paddingStart
}

internal fun View.getRightPadding(): Int {
    if (paddingEnd == 0) {
        return paddingRight
    }
    return paddingEnd
}

internal fun View.pxToDp(px: Int): Int {
    val density = resources.displayMetrics.densityDpi
    return px / (density / DisplayMetrics.DENSITY_DEFAULT)
}

internal fun View.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.densityDpi
    return dp * (density / DisplayMetrics.DENSITY_DEFAULT)
}

internal fun View.spToFloat(sp: Int): Float {
    val metrics = resources.displayMetrics
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp.toFloat(),
        metrics
    )
}