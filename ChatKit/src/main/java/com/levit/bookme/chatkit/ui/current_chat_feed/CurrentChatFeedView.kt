package com.levit.bookme.chatkit.ui.current_chat_feed

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.chat_kit.databinding.CurrentChatFeedLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderListener
import com.levit.bookme.chatkit.ui.message_input.MessageInputButtonListener
import com.levit.bookme.chatkit.ui.message_input.MessageInputTextChangeListener

@Suppress("JoinDeclarationAndAssignment")
class CurrentChatFeedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    var styleOptions: CurrentChatHeaderStyleOptions = CurrentChatHeaderStyleOptions.provideDefaultOptions()
        set(value) {
            field = value
            applyCurrentChatFeedStyleOptions(value)
        }

    var currentChatFeedModel: CurrentChatFeedModel = provideEmptyModel()
        set(value) {
            field = value
            applyCurrentChatFeedModel(value)
        }

    var chatFeedHeaderListener: CurrentChatHeaderListener? = null

    var messageInputButtonListener: MessageInputButtonListener? = null
    var messageInputTextChangeListener: MessageInputTextChangeListener? = null

    private val binding: CurrentChatFeedLayoutBinding

    init {
        binding = CurrentChatFeedLayoutBinding.inflate(inflater, this, true)
    }

    fun addMessageDecorator(decorator: RecyclerView.ItemDecoration) {
        binding.messagesRecycler.addItemDecoration(decorator)
    }

    fun addMessageDecorator(decorator: RecyclerView.ItemDecoration, index: Int) {
        binding.messagesRecycler.addItemDecoration(decorator, index)
    }

    private fun applyCurrentChatFeedStyleOptions(options: CurrentChatHeaderStyleOptions) {

    }

    private fun applyCurrentChatFeedModel(model: CurrentChatFeedModel) {

    }
}