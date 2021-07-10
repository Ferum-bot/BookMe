package com.levit.bookme.chatkit.ui.chat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.levit.book_me.chat_kit.R
import com.levit.book_me.chat_kit.databinding.ChatLayoutBinding
import com.levit.bookme.chatkit.extensions.dpToPx
import com.levit.bookme.chatkit.extensions.emptyChatModel
import com.levit.bookme.chatkit.extensions.setMarginsDp
import com.levit.bookme.chatkit.extensions.setTextSizeSp
import com.levit.bookme.chatkit.models.chat.ChatDateParser
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.enums.ChatLastMessageFrom
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.utills.RemoteImageLoader
import com.levit.bookme.chatkit.drawables.RoundedDrawable

class ChatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    var styleOptions: ChatStyleOptions = ChatStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyStyleOptions(value)
        }

    var chatModel: ChatModel = emptyChatModel()
        set(value) {
            field = value
            applyChatModel(value)
        }

    private val binding: ChatLayoutBinding

    private val imageLoader: RemoteImageLoader

    private val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    init {
        binding = ChatLayoutBinding.inflate(inflater, this, true)

        imageLoader = RemoteImageLoader(binding.interlocutorProfileImage, defaultGlideOptions())
    }

    private fun applyStyleOptions(options: ChatStyleOptions) {
        applyGeneralOptions(options)
        applyInterlocutorNameOptions(options)
        applyInterlocutorProfileIconOptions(options)
        applyLastMessageDateOptions(options)
        applyLastMessageOptions(options)
    }

    private fun applyChatModel(model: ChatModel) {
        val interlocutorName = model.interlocutorName
        val lastMessage = model.lastMessage
        val lastMessageType = model.lastMessageFrom
        val dateOfLastMessage = model.dateOfLastMessage
        val profileUrl = model.interlocutorProfileImageUrl

        binding.interlocutorName.text = interlocutorName
        binding.lastMessageText.text = adaptLastMessage(lastMessage, lastMessageType)
        binding.lastMessageDate.text = ChatDateParser.parseDateWithFormat(
            date = dateOfLastMessage,
            format = styleOptions.lastMessageDateFormat
        )
        imageLoader.load(profileUrl)
    }

    private fun applyInterlocutorNameOptions(options: ChatStyleOptions)
    = with(binding) {
        interlocutorName.setTextColor(options.interlocutorNameTextColor)
        interlocutorName.setTextSizeSp(options.interlocutorNameTextSizeSp)
        interlocutorName.setMarginsDp(
            left = options.interlocutorNameMarginStartDp,
            top = options.interlocutorNameMarginTopDp,
            right = options.interlocutorNameMarginEndDp,
            bottom = options.interlocutorNameMarginBottomDp,
        )

        when(options.interlocutorNameAlignment) {
            MessageTextAlignment.START -> alignInterlocutorNameToStart()
            MessageTextAlignment.END -> alignInterlocutorNameToEnd()
        }
    }

    private fun alignInterlocutorNameToStart() {
        val layout = binding.backgroundLayout
        val nameView = binding.interlocutorName
        val layoutId = layout.id
        val nameViewId = nameView.id
        val constraintSet = ConstraintSet()

        constraintSet.connect(nameViewId, ConstraintSet.START, layoutId, ConstraintSet.START)
        constraintSet.connect(nameViewId, ConstraintSet.TOP, layoutId, ConstraintSet.TOP)
        constraintSet.applyTo(layout)
    }

    private fun alignInterlocutorNameToEnd() {
        val layout = binding.backgroundLayout
        val nameView = binding.interlocutorName
        val layoutId = layout.id
        val nameViewId = nameView.id
        val constraintSet = ConstraintSet()

        constraintSet.connect(nameViewId, ConstraintSet.END, layoutId, ConstraintSet.END)
        constraintSet.connect(nameViewId, ConstraintSet.TOP, layoutId, ConstraintSet.TOP)
        constraintSet.applyTo(layout)
    }

    private fun applyLastMessageOptions(options: ChatStyleOptions)
    = with(binding) {
        lastMessageText.setTextColor(options.lastMessageTextColor)
        lastMessageText.setTextSizeSp(options.lastMessageTextSizeSp)
        lastMessageText.setMarginsDp(
            left = options.lastMessageMarginStartDp,
            top = options.lastMessageMarginTopDp,
            right = options.lastMessageMarginEndDp,
            bottom = options.lastMessageMarginBottomDp,
        )
    }

    private fun applyInterlocutorProfileIconOptions(options: ChatStyleOptions)
    = with(binding) {
        interlocutorProfileImage.isVisible = options.showProfileIcon
        interlocutorProfileImage.setMarginsDp(
            left = options.profileIconMarginStartDp,
            top = options.profileIconMarginTopDp,
            right = options.profileIconMarginEndDp,
            bottom = options.profileIconMarginBottomDp,
        )
    }

    private fun applyLastMessageDateOptions(options: ChatStyleOptions)
    = with(binding) {
        lastMessageDate.isVisible = options.showLastMessageDate
        lastMessageDate.setTextColor(options.lastMessageDateTextColor)
        lastMessageDate.setTextSizeSp(options.lastMessageDateTextSizeSp)
        lastMessageDate.setMarginsDp(
            left = options.lastMessageDateMarginStartDp,
            top = options.lastMessageDateMarginTopDp,
            right = options.lastMessageDateMarginEndDp,
            bottom = options.lastMessageDateMarginBottomDp,
        )

        when(options.lastMessageDateAlignment) {
            MessageTextAlignment.START -> alignLastMessageDateToStart()
            MessageTextAlignment.END -> alignLastMessageDateToEnd()
        }
    }

    private fun alignLastMessageDateToStart() {
        val layout = binding.backgroundLayout
        val dateView = binding.lastMessageDate
        val layoutId = layout.id
        val dateViewId = dateView.id
        val constraintSet = ConstraintSet()

        constraintSet.connect(dateViewId, ConstraintSet.START, layoutId, ConstraintSet.START)
        constraintSet.connect(dateViewId, ConstraintSet.BOTTOM, layoutId, ConstraintSet.BOTTOM)
        constraintSet.applyTo(layout)
    }

    private fun alignLastMessageDateToEnd() {
        val layout = binding.backgroundLayout
        val dateView = binding.lastMessageDate
        val layoutId = layout.id
        val dateViewId = dateView.id
        val constraintSet = ConstraintSet()

        constraintSet.connect(dateViewId, ConstraintSet.END, layoutId, ConstraintSet.END)
        constraintSet.connect(dateViewId, ConstraintSet.BOTTOM, layoutId, ConstraintSet.BOTTOM)
        constraintSet.applyTo(layout)
    }

    private fun applyGeneralOptions(options: ChatStyleOptions)
    = with(binding) {
        val backgroundDrawable = RoundedDrawable(
            backgroundColor = options.backgroundColor,
            radiusTopLeftPx = dpToPx(options.backgroundTopLeftRadiusDp) ?: 0,
            radiusTopRightPx = dpToPx(options.backgroundTopRightRadiusDp) ?: 0,
            radiusBottomLeftPx = dpToPx(options.backgroundBottomLeftRadiusDp) ?: 0,
            radiusBottomRightPx = dpToPx(options.backgroundBottomRightRadiusDp) ?: 0,
        )
    }

    private fun defaultGlideOptions() = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.default_image_placeholder)
        .error(R.drawable.ic_default_error_placeholder)

    private fun adaptLastMessage(lastMessage: String?, from: ChatLastMessageFrom): String {
        lastMessage ?: return ""
        return when(from) {
            ChatLastMessageFrom.MESSAGE_FROM_YOU ->
                "You: $lastMessage"
            ChatLastMessageFrom.MESSAGE_FROM_INTERLOCUTOR ->
                lastMessage
        }
    }
}