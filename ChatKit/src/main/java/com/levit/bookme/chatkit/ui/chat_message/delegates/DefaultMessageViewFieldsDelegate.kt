package com.levit.bookme.chatkit.ui.chat_message.delegates

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.levit.bookme.chatkit.extensions.setMarginsDp
import com.levit.bookme.chatkit.extensions.setTextSizeSp
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment
import com.levit.bookme.chatkit.drawables.RoundedDrawable
import de.hdodenhof.circleimageview.CircleImageView

/**
 * See @MessageViewFieldsDelegate to more information.
 */
internal class DefaultMessageViewFieldsDelegate(
    private val dpToPx: (dp: Int?) -> Int?,
): MessageViewFieldsDelegate {

    override fun applyOptionsToAuthorLabel(
        layout: ConstraintLayout, authorView: TextView, options: MessageStyleOptions
    ) {
        authorView.setTextColor(options.authorColor)
        authorView.setTextSizeSp(options.authorLabelSizeSP)
        authorView.setMarginsDp(
            left = options.authorLabelMarginStartDp,
            top = options.authorLabelMarginTopDp,
            right = options.authorLabelMarginEndDp,
            bottom = options.authorLabelMarginBottomDp,
        )
        authorView.isVisible = options.showAuthorLabel

        when (options.authorLabelAlignment) {
            MessageTextAlignment.START -> alignAuthorLabelToStart(layout, authorView)
            MessageTextAlignment.END -> alignAuthorLabelToEnd(layout, authorView)
        }
    }

    override fun applyOptionsToProfileIcon(
        layout: ConstraintLayout, profileView: CircleImageView, options: MessageStyleOptions
    ) {
        profileView.isVisible = options.showProfileImage
    }

    override fun applyOptionsToMessageText(
        layout: ConstraintLayout, textView: TextView, options: MessageStyleOptions
    ) {
        textView.setTextColor(options.textColor)
        textView.setTextSizeSp(options.textSizeSP)
        textView.setMarginsDp(
            left = options.textMarginStartDP,
            top = options.textMarginTopDP,
            right = options.textMarginEndDP,
            bottom = options.textMarginBottom,
        )
    }

    override fun applyOptionsToDateLabel(
        layout: ConstraintLayout, dateView: TextView, options: MessageStyleOptions
    ) {
        dateView.setTextColor(options.dateColor)
        dateView.setTextSizeSp(options.dateLabelSizeSP)
        dateView.setMarginsDp(
            left = options.dateLabelMarginStartDp,
            top = options.dateLabelMarginTopDp,
            right = options.dateLabelMarginEndDp,
            bottom = options.dateLabelMarginBottomDp,
        )
        dateView.isVisible = options.showDateLabel

        when(options.dateLabelAlignment) {
            MessageTextAlignment.START -> alignDateLabelToStart(layout, dateView)
            MessageTextAlignment.END -> alignDateLabelToEnd(layout, dateView)
        }
    }

    override fun applyOptionsToMessageLayout(
        layout: ConstraintLayout, options: MessageStyleOptions
    ) {
        val backgroundColor = options.messageBackgroundColor
        val topLeftRadiusPx = dpToPx(options.messageBackgroundTopLeftCornerRadiusDP) ?: 0
        val topRightRadiusPx = dpToPx(options.messageBackgroundTopRightCornerRadiusDP) ?: 0
        val bottomRightRadiusPx = dpToPx(options.messageBackgroundBottomRightCornerRadiusDP) ?: 0
        val bottomLeftRadiusPx = dpToPx(options.messageBackgroundBottomLeftCornerRadiusDP) ?: 0
        val roundedDrawable = RoundedDrawable(
            backgroundColor = backgroundColor,
            radiusTopLeftPx = topLeftRadiusPx,
            radiusTopRightPx = topRightRadiusPx,
            radiusBottomRightPx = bottomRightRadiusPx,
            radiusBottomLeftPx = bottomLeftRadiusPx,
        )

        layout.background = roundedDrawable
    }

    private fun alignAuthorLabelToStart(layout: ConstraintLayout, authorView: TextView) {
        val layoutId = layout.id
        val authorId = authorView.id
        val constraintSet = ConstraintSet().apply {
            clone(layout)
        }

        constraintSet.connect(authorId, ConstraintSet.START, layoutId, ConstraintSet.START)
        constraintSet.connect(authorId, ConstraintSet.TOP, layoutId, ConstraintSet.TOP)
        constraintSet.applyTo(layout)
    }

    private fun alignAuthorLabelToEnd(layout: ConstraintLayout, authorView: TextView) {
        val layoutId = layout.id
        val authorId = authorView.id
        val constraintSet = ConstraintSet().apply {
            clone(layout)
        }

        constraintSet.connect(authorId, ConstraintSet.END, layoutId, ConstraintSet.END)
        constraintSet.connect(authorId, ConstraintSet.TOP, layoutId, ConstraintSet.TOP)
        constraintSet.applyTo(layout)
    }

    private fun alignDateLabelToStart(layout: ConstraintLayout, dateView: TextView) {
        val layoutId = layout.id
        val dateId = dateView.id
        val constraintSet = ConstraintSet().apply {
            clone(layout)
        }

        constraintSet.connect(dateId, ConstraintSet.START, layoutId, ConstraintSet.START)
        constraintSet.connect(dateId, ConstraintSet.BOTTOM, layoutId, ConstraintSet.BOTTOM)
        constraintSet.applyTo(layout)
    }

    private fun alignDateLabelToEnd(layout: ConstraintLayout, dateView: TextView) {
        val layoutId = layout.id
        val dateId = dateView.id
        val constraintSet = ConstraintSet().apply {
            clone(layout)
        }

        constraintSet.connect(dateId, ConstraintSet.END, layoutId, ConstraintSet.END)
        constraintSet.connect(dateId, ConstraintSet.BOTTOM, layoutId, ConstraintSet.BOTTOM)
        constraintSet.applyTo(layout)
    }
}