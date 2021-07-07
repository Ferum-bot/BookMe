package com.levit.bookme.chatkit.ui.chat_message.delegates

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.bookme.chatkit.models.MessageStyleOptions
import de.hdodenhof.circleimageview.CircleImageView

/**
 * This interface used to delegate work from applying message style options
 * from specific MessageView(YourMessageView or InterlocutorMessageView) to
 * concrete interior ...MessageView views.
 *
 * Needed to remove duplicate code.
 */
internal interface MessageViewFieldsDelegate {

    fun applyOptionsToAuthorLabel(layout: ConstraintLayout, authorView: TextView, options: MessageStyleOptions)

    fun applyOptionsToMessageText(layout: ConstraintLayout, textView: TextView, options: MessageStyleOptions)

    fun applyOptionsToDateLabel(layout: ConstraintLayout, dateView: TextView, options: MessageStyleOptions)

    fun applyOptionsToProfileIcon(layout: ConstraintLayout, profileView: CircleImageView, options: MessageStyleOptions)

    fun applyOptionsToMessageLayout(layout: ConstraintLayout, options: MessageStyleOptions)

}