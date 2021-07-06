package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.levit.book_me.chat_kit.databinding.YourChatMessageLayoutBinding
import com.levit.bookme.chatkit.models.MessageDateParser
import com.levit.bookme.chatkit.models.MessageStyleOptions
import com.levit.bookme.chatkit.models.interfaces.MessageModel
import com.levit.bookme.chatkit.models.utills.RemoteImageLoader
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import java.util.*

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

    init {
        binding = YourChatMessageLayoutBinding.inflate(inflater, this, true)
        profileImageLoader = RemoteImageLoader(binding.profileImage, defaultGlideOptions())
    }

    override fun applyStyleOptions(options: MessageStyleOptions) {

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

}