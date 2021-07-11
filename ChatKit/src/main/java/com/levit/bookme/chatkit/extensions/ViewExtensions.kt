package com.levit.bookme.chatkit.extensions

import android.graphics.Color
import android.graphics.Paint
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.*
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.levit.book_me.chat_kit.R

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

/**
 * Null means that margin will not changed.
 */
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

/**
 * Null means that padding will not changed.
 */
internal fun View.setPaddingDp(
    left: Int? = null, top: Int? = null,
    right: Int? = null, bottom: Int? = null,
) {
    val leftPx = dpToPx(left)
    val topPx = dpToPx(top)
    val rightPx = dpToPx(right)
    val bottomPx = dpToPx(bottom)

    setPaddingPx(
        left = leftPx, top = topPx, right = rightPx, bottom = bottomPx,
    )
}

/**
 * Null means that padding will not changed.
 */
internal fun View.setPaddingPx(
    left: Int? = null, top: Int? = null,
    right: Int? = null, bottom: Int? = null,
) {
    val newLeft = left ?: paddingLeft
    val newTop = top ?: paddingTop
    val newRight = right ?: paddingRight
    val newBottom = bottom ?: paddingBottom

    setPadding(newLeft, newTop, newRight, newBottom)
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

internal fun View.getString(@StringRes id: Int): String {
    return context.getString(id)
}

internal fun View.defaultGlideOptions() = RequestOptions()
    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
    .placeholder(defaultPlaceHolder())
    .error(R.drawable.ic_default_error_placeholder)

internal fun View.defaultPlaceHolder() = CircularProgressDrawable(context).apply {
    strokeWidth = 2f
    centerRadius = 10f
    colorFilter = BlendModeColorFilterCompat
        .createBlendModeColorFilterCompat(R.color.black, BlendModeCompat.SRC_IN)
    start()
}