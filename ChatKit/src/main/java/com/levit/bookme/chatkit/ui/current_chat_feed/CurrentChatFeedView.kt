package com.levit.bookme.chatkit.ui.current_chat_feed

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.chat_kit.R
import com.levit.book_me.chat_kit.databinding.CurrentChatFeedLayoutBinding
import com.levit.bookme.chatkit.decorators.BottomExtraSpaceDecorator
import com.levit.bookme.chatkit.decorators.TopExtraSpaceDecorator
import com.levit.bookme.chatkit.extensions.*
import com.levit.bookme.chatkit.extensions.getString
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.extensions.setMarginsDp
import com.levit.bookme.chatkit.factories.impl.DefaultChatKitViewFactoryFacade
import com.levit.bookme.chatkit.models.chat_messages.InterlocutorMessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.chat_messages.YourMessageModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.models.enums.ChatFeedEmptyMessageFormat
import com.levit.bookme.chatkit.models.enums.MessageType
import com.levit.bookme.chatkit.models.enums.ScrollStates
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.recycler.adapters.CurrentFeedMessagesAdapter
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
            applyEmptyMessageStyleOptions(value)
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
            applyInterlocutorMessageStyleOptions()
        }

    var yourMessageStyleOptions = MessageStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyYourMessageStyleOptions()
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

    var feedListener: CurrentChatFeedListener? = null

    var isInterlocutorOnline: Boolean = false
        set(value) {
            field = value
            applyOnlineStatus(value)
        }

    var emptyChatTextMessage: String = getString(R.string.empty_chat_text_message)
        set(value) {
            field = value
            applyEmptyChatTextMessage(value)
        }

    var emptyChatImageMessage: Drawable? = null
        set(value) {
            field = value
            applyEmptyChatImageMessage(value)
        }

    private val binding: CurrentChatFeedLayoutBinding

    private val chatKitFactory by lazy {
        DefaultChatKitViewFactoryFacade()
    }

    private var messagesAdapter = CurrentFeedMessagesAdapter(
        chatKitFactory,
        yourMessageStyleOptions = yourMessageStyleOptions,
        interlocutorMessageStyleOptions = interlocutorMessageStyleOptions,
    )

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
        binding.messagesRecycler.adapter = messagesAdapter

        val scrollListener = object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                when(newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING ->
                        feedListener?.onScrollStateChanged(ScrollStates.SCROLL_STATE_DRAGGING)
                    RecyclerView.SCROLL_STATE_SETTLING ->
                        feedListener?.onScrollStateChanged(ScrollStates.SCROLL_STATE_SETTLING)
                    RecyclerView.SCROLL_STATE_IDLE ->
                        feedListener?.onScrollStateChanged(ScrollStates.SCROLL_STATE_IDLE)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                feedListener?.onScrolled(dx, dy)
            }
        }
        binding.messagesRecycler.addOnScrollListener(scrollListener)
    }

    private fun applyCurrentChatFeedStyleOptions(options: CurrentChatFeedStyleOptions) = with(binding) {
        val topDecorator = TopExtraSpaceDecorator(options.messagesLayoutExtraSpaceTopDp)
        val bottomDecorator = BottomExtraSpaceDecorator(options.messagesLayoutExtraSpaceBottomDp)

        messagesRecycler.setBackgroundColor(options.messagesLayoutBackgroundColor)
        messagesRecycler.addItemDecoration(topDecorator)
        messagesRecycler.addItemDecoration(bottomDecorator)
        messagesRecycler.setMarginsDp(
            left = options.messagesLayoutExtraSpaceStartDp,
            right = options.messagesLayoutExtraSpaceEndDp,
        )
    }

    private fun applyHeaderStyleOptions(options: CurrentChatHeaderStyleOptions) {
        binding.interlocutorHeader.styleOptions = options
    }

    private fun applyMessageInputStyleOptions(options: MessageInputStyleOptions) {
        binding.messageInput.styleOptions = options
    }

    private fun applyInterlocutorMessageStyleOptions() {
        messagesAdapter = CurrentFeedMessagesAdapter(
            chatKitFactory,
            yourMessageStyleOptions = yourMessageStyleOptions,
            interlocutorMessageStyleOptions = interlocutorMessageStyleOptions,
        )
        messagesAdapter.items = currentChatFeedModel.allMessages.parseForAdapter()
        binding.messagesRecycler.adapter = messagesAdapter
    }

    private fun applyYourMessageStyleOptions() {
        messagesAdapter = CurrentFeedMessagesAdapter(
            chatKitFactory,
            yourMessageStyleOptions = yourMessageStyleOptions,
            interlocutorMessageStyleOptions = interlocutorMessageStyleOptions,
        )
        messagesAdapter.items = currentChatFeedModel.allMessages.parseForAdapter()
        binding.messagesRecycler.adapter = messagesAdapter
    }

    private fun applyEmptyMessageStyleOptions(options: CurrentChatFeedStyleOptions)
    = with(binding) {
        emptyChatText.setTextColor(options.emptyChatFeedTextMessageColor)
        emptyChatText.setTextSizeSp(options.emptyChatFeedTextMessageSizeSp)

        when(options.emptyChatFeedFormat) {
            ChatFeedEmptyMessageFormat.SHOW_ONLY_TEXT -> {
                emptyChatImage.visibility = View.INVISIBLE
                emptyChatText.visibility = View.VISIBLE
            }
            ChatFeedEmptyMessageFormat.SHOW_ONLY_IMAGE -> {
                emptyChatImage.visibility = View.VISIBLE
                emptyChatText.visibility = View.GONE
            }
            ChatFeedEmptyMessageFormat.SHOW_TEXT_AND_IMAGE -> {
                emptyChatImage.visibility = View.VISIBLE
                emptyChatText.visibility = View.VISIBLE
            }
            ChatFeedEmptyMessageFormat.SHOW_NOTHING -> {
                emptyChatImage.visibility = View.GONE
                emptyChatText.visibility = View.GONE
            }
        }
    }

    private fun applyCurrentChatFeedModel(model: CurrentChatFeedModel) {
        val messages = model.allMessages
        messagesAdapter.items = messages.parseForAdapter()
        CurrentFeedMessagesDelegates.allMessages = messages

        messages.ifEmpty { showEmptyChat(true) }
        messages.ifNotEmpty { showEmptyChat(false) }
    }

    private fun applyProfileHeaderModel(model: CurrentChatHeaderModel?) {
        model ?: return
        binding.interlocutorHeader.chatHeaderModel = model
    }

    private fun applyMessageInputModel(model: MessageInputModel?) {
        model ?: return
        binding.messageInput.messageInputModel = model
    }

    private fun List<MessageModel>.parseForAdapter() = map { currentModel ->
        when(currentModel.type) {
            MessageType.YOUR_MESSAGE -> YourMessageModel(currentModel)
            MessageType.INTERLOCUTOR_MESSAGE -> InterlocutorMessageModel(currentModel)
        }
    }

    private fun applyOnlineStatus(isOnline: Boolean) {
        binding.interlocutorHeader.isOnline = isOnline
    }

    private fun applyEmptyChatTextMessage(message: String) {
        binding.emptyChatText.text = message
    }

    private fun applyEmptyChatImageMessage(drawable: Drawable?) {
        binding.emptyChatImage.background = drawable
    }

    private fun showEmptyChat(show: Boolean) {
        binding.messagesRecycler.isVisible = !show
        binding.emptyChatText.isVisible = show
        binding.emptyChatImage.isVisible = show
        applyEmptyMessageStyleOptions(currentChatFeedStyleOptions)
    }
}