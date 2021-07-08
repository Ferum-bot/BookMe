package com.levit.bookme.chatkit.extensions

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

/**
 * Null means that margin will not changed.
 */
internal fun View.setMarginsDp(
    left: Int? = null, top: Int? = null,
    right: Int? = null, bottom: Int? = null,
) {
    val leftPx = dpToPx(left)
    val topPx = dpToPx(top)
    val rightPx = dpToPx(right)
    val bottomPx = dpToPx(bottom)
    setMarginsPx(
        left = leftPx, top = topPx, right = rightPx, bottom = bottomPx,
    )
}

internal fun View.setMarginsPx(
    left: Int? = null, top: Int? = null,
    right: Int? = null, bottom: Int? = null,
) {
    val newLeft = left ?: marginLeft
    val newTop = top ?: marginTop
    val newRight = right ?: marginRight
    val newBottom = bottom ?: marginBottom

    val params = layoutParams as? ViewGroup.MarginLayoutParams ?: return
    params.setMargins(newLeft, newTop, newRight, newBottom)
}

internal fun View.dpToPx(dp: Int?): Int? {
    dp ?: return null

    val metrics = resources.displayMetrics
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        metrics
    ).toInt()
}

internal val View.inflater: LayoutInflater
    get() = LayoutInflater.from(context)