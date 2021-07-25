package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import com.levit.book_me.chat_kit.databinding.InterlocutorChatMessageLayoutBinding
import com.levit.bookme.chatkit.extensions.dpToPx
import com.levit.bookme.chatkit.models.chat_messages.MessageDateParser
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.utills.RemoteImageLoader
import com.levit.bookme.chatkit.ui.chat_message.delegates.DefaultMessageViewFieldsDelegate
import com.levit.bookme.chatkit.ui.chat_message.delegates.MessageViewFieldsDelegate

@Suppress("JoinDeclarationAndAssignment")
internal class InterlocutorMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): MessageView(context, attrs, defStyleAttr) {

    override val binding: InterlocutorChatMessageLayoutBinding

    override val profileImageLoader: RemoteImageLoader

    override var isFirstMessage: Boolean = true
        set(value) {
            field = value
            showAuthorLabel(value)
            showProfileIcon(value)
        }

    private val fieldsDelegate: MessageViewFieldsDelegate

    init {
        binding = InterlocutorChatMessageLayoutBinding.inflate(inflater, this, true)

        profileImageLoader = RemoteImageLoader(binding.profileImage, defaultGlideOptions())

        fieldsDelegate = DefaultMessageViewFieldsDelegate(this::dpToPx)

        setAllClickListeners()
    }

    override fun applyStyleOptions(options: MessageStyleOptions) {
        configureAuthorLabel(options)
        configureDateLabel(options)
        configureGeneralView(options)
        configureProfileIcon(options)
        configureText(options)
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

    override fun setAllClickListeners() {
        binding.text.setOnClickListener {
            listener?.onMessageClicked(messageModel)
        }

        binding.text.setOnLongClickListener {
            if (listener == null) {
                return@setOnLongClickListener false
            }
            listener?.onMessageLongClicked(messageModel)
            return@setOnLongClickListener true
        }

        binding.profileImage.setOnClickListener {
            listener?.onProfileIconClicked(messageModel)
        }

        binding.profileImage.setOnLongClickListener {
            if (listener == null) {
                return@setOnLongClickListener false
            }
            listener?.onProfileIconLongClicked(messageModel)
            return@setOnLongClickListener true
        }

        binding.authorLabel.setOnClickListener {
            listener?.onAuthorNameClicked(messageModel)
        }
    }

    private fun showAuthorLabel(show: Boolean) {
        binding.authorLabel.isVisible = show
    }

    private fun showProfileIcon(show: Boolean) {
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
            null, options,
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