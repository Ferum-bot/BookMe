package com.levit.bookme.chatkit.ui.current_chat_feed

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.chat_kit.databinding.CurrentChatFeedLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.recycler.delegates.CurrentFeedMessagesDelegates
import com.levit.bookme.chatkit.ui.chat_message.MessageListener
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderListener
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew
import com.levit.bookme.chatkit.ui.message_input.MessageInputButtonListener
import com.levit.bookme.chatkit.ui.message_input.MessageInputTextChangeListener

@Suppress("JoinDeclarationAndAssignment")
class CurrentChatFeedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {


    /**
     * All style options.
     */
    var currentChatFeedStyleOptions = CurrentChatFeedStyleOptions.provideDefaultOptions()
        set(value) {
            field = value
            applyCurrentChatFeedStyleOptions(value)
        }

    var headerStyleOptions = CurrentChatHeaderStyleOptions.provideDefaultOptions()
        set(value) {
            field = value
            applyHeaderStyleOptions(value)
        }

    var messageInputStyleOptions = MessageInputStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyMessageInputStyleOptions(value)
        }

    var interlocutorMessageStyleOptions = MessageStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyInterlocutorMessageStyleOptions(value)
        }

    var yourMessageStyleOptions = MessageStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyYourMessageStyleOptions(value)
        }


    /**
     * All view models.
     */
    var currentChatFeedModel = provideEmptyModel()
        set(value) {
            field = value
            applyCurrentChatFeedModel(value)
        }

    var profileHeaderModel: CurrentChatHeaderModel? = null
        set(value) {
            field = value
            applyProfileHeaderModel(value)
        }

    var messageInputModel: MessageInputModel? = null
        set(value) {
            field = value
            applyMessageInputModel(value)
        }


    /**
     * All view listeners.
     */
    var chatFeedHeaderListener: CurrentChatHeaderListener? = null
        set(value) {
            field = value
            binding.interlocutorHeader.listener = value
        }

    var messageListener: MessageListener? = null
        set(value) {
            field = value
            CurrentFeedMessagesDelegates.messageListener = value
        }

    var messageInputButtonListener: MessageInputButtonListener? = null
        set(value) {
            field = value
            binding.messageInput.messageSendListener = value
        }
    var messageInputTextChangeListener: MessageInputTextChangeListener? = null
        set(value) {
            field = value
            binding.messageInput.textChangeListener = value
        }



    private val binding: CurrentChatFeedLayoutBinding

    init {
        binding = CurrentChatFeedLayoutBinding.inflate(inflater, this, true)

        setUpMessageRecycler()
    }

    fun addMessageDecorator(decorator: RecyclerView.ItemDecoration) {
        binding.messagesRecycler.addItemDecoration(decorator)
    }

    fun addMessageDecorator(decorator: RecyclerView.ItemDecoration, index: Int) {
        binding.messagesRecycler.addItemDecoration(decorator, index)
    }

    fun removeMessageDecorator(decorator: RecyclerView.ItemDecoration) {
        binding.messagesRecycler.removeItemDecoration(decorator)
    }

    fun removeMessageDecoratorAt(index: Int) {
        binding.messagesRecycler.removeItemDecorationAt(index)
    }

    fun getMessageDecorationAt(index: Int): RecyclerView.ItemDecoration {
        return binding.messagesRecycler.getItemDecorationAt(index)
    }

    fun invalidateMessageDecorations() {
        binding.messagesRecycler.invalidateItemDecorations()
    }

    private fun setUpMessageRecycler() {

    }

    private fun applyCurrentChatFeedStyleOptions(options: CurrentChatFeedStyleOptions) {

    }

    private fun applyHeaderStyleOptions(options: CurrentChatHeaderStyleOptions) {

    }

    private fun applyMessageInputStyleOptions(options: MessageInputStyleOptions) {

    }

    private fun applyInterlocutorMessageStyleOptions(options: MessageStyleOptions) {

    }

    private fun applyYourMessageStyleOptions(options: MessageStyleOptions) {

    }

    private fun applyCurrentChatFeedModel(model: CurrentChatFeedModel) {

    }

    private fun applyProfileHeaderModel(model: CurrentChatHeaderModel?) {

    }

    private fun applyMessageInputModel(model: MessageInputModel?) {

    }
}