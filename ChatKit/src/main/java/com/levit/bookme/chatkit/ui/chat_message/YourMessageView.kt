package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import com.levit.book_me.chat_kit.databinding.YourChatMessageLayoutBinding
import com.levit.bookme.chatkit.extensions.dpToPx
import com.levit.bookme.chatkit.models.MessageDateParser
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.interfaces.MessageModel
import com.levit.bookme.chatkit.models.utills.RemoteImageLoader
import com.levit.bookme.chatkit.ui.chat_message.delegates.DefaultMessageViewFieldsDelegate
import com.levit.bookme.chatkit.ui.chat_message.delegates.MessageViewFieldsDelegate

@Suppress("JoinDeclarationAndAssignment")
internal class YourMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): MessageView(context, attrs, defStyleAttr) {

    override val binding: YourChatMessageLayoutBinding

    override val profileImageLoader: RemoteImageLoader

    override var isFirstMessage: Boolean = true
        set(value) {
            field = value
            showAuthorLabel(value)
            showProfileIcon(value)
        }

    private val fieldsDelegate: MessageViewFieldsDelegate

    init {
        binding = YourChatMessageLayoutBinding.inflate(inflater, this, true)

        profileImageLoader = RemoteImageLoader(binding.profileImage, defaultGlideOptions())

        fieldsDelegate = DefaultMessageViewFieldsDelegate(this::dpToPx)
    }

    override fun applyStyleOptions(options: MessageStyleOptions) {
        configureAuthorLabel(options)
        configureText(options)
        configureDateLabel(options)
        configureGeneralView(options)
        configureProfileIcon(options)
    }

    override fun applyMessageModel(model: MessageModel) {
        val author = model.author
        val text = model.text
        val date = MessageDateParser.parseDateWithFormat(model.date, styleOptions.dateShowFormat)
        val profileUrl = model.authorImageUrlLink

        binding.authorLabel.text = author
        binding.text.text = text
        binding.dateLabel.text = date
        profileImageLoader.load(profileUrl)
    }

    private fun showAuthorLabel(show: Boolean) {
        if (!styleOptions.showAuthorLabel) {
            return
        }
        binding.authorLabel.isVisible = show
    }

    private fun showProfileIcon(show: Boolean) {
        if (!styleOptions.showProfileImage) {
            return
        }
        binding.profileImage.visibility =
            if (show) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
    }

    private fun configureAuthorLabel(options: MessageStyleOptions) = with(binding) {
        fieldsDelegate.applyOptionsToAuthorLabel(
            layout = messageLayout,
            authorView = authorLabel,
            options
        )
    }

    private fun configureText(options: MessageStyleOptions) = with(binding) {
        fieldsDelegate.applyOptionsToMessageText(
            layout = messageLayout,
            textView = text,
            options
        )
    }

    private fun configureDateLabel(options: MessageStyleOptions) = with(binding) {
        fieldsDelegate.applyOptionsToDateLabel(
            layout = messageLayout,
            dateView = dateLabel,
            options
        )
    }

    private fun configureProfileIcon(options: MessageStyleOptions) = with(binding) {
        fieldsDelegate.applyOptionsToProfileIcon(
            layout = messageLayout,
            profileView = profileImage,
            options
        )
    }

    private fun configureGeneralView(options: MessageStyleOptions) = with(binding) {
        fieldsDelegate.applyOptionsToMessageLayout(
            layout = messageLayout,
            options
        )
    }

}