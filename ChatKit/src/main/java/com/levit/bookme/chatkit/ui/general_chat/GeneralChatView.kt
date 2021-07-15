package com.levit.bookme.chatkit.ui.general_chat

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.chat_kit.databinding.GeneralChatLayoutBinding
import com.levit.bookme.chatkit.decorators.BottomExtraSpaceDecorator
import com.levit.bookme.chatkit.decorators.TopExtraSpaceDecorator
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.extensions.setMarginsDp
import com.levit.bookme.chatkit.factories.impl.DefaultChatKitViewFactoryFacade
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.enums.ScrollStates
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel
import com.levit.bookme.chatkit.models.general_chat.GeneralChatStyleOptions
import com.levit.bookme.chatkit.recycler.adapters.GeneralChatAdapter
import com.levit.bookme.chatkit.recycler.delegates.GeneralChatDelegates
import com.levit.bookme.chatkit.ui.chat.ChatListener

@Suppress("JoinDeclarationAndAssignment")
class GeneralChatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {


    /**
     * All view models.
     */
    var generalChatModel = provideEmptyModel()
        set(value) {
            field = value
            applyGeneralChatModel(value)
        }


    /**
     * All style options.
     */
    var generalStyleOptions = GeneralChatStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyGeneralChatStyleOptions(value)
        }

    var chatStyleOptions = ChatStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyChatStyleOptions(value)
        }


    /**
     * All view listeners.
     */
    var chatListener: ChatListener? = null
        set(value) {
            field = value
            GeneralChatDelegates.chatListener = value
        }

    var generalViewListener: GeneralChatListener? = null



    private val binding: GeneralChatLayoutBinding

    private val chatKitFactory by lazy {
        DefaultChatKitViewFactoryFacade()
    }

    private var chatsAdapter = GeneralChatAdapter(chatKitFactory, chatStyleOptions)

    init {
        binding = GeneralChatLayoutBinding.inflate(inflater, this, true)

        setUpRecyclerView()
    }

    fun addChatDecorator(decorator: RecyclerView.ItemDecoration) {
        binding.chatsRecycler.addItemDecoration(decorator)
    }

    fun addChatDecorator(decorator: RecyclerView.ItemDecoration, index: Int) {
        binding.chatsRecycler.addItemDecoration(decorator, index)
    }

    fun removeChatDecorator(decorator: RecyclerView.ItemDecoration) {
        binding.chatsRecycler.removeItemDecoration(decorator)
    }

    fun removeChatDecoratorAt(index: Int) {
        binding.chatsRecycler.removeItemDecorationAt(index)
    }

    fun getChatDecorationAt(index: Int): RecyclerView.ItemDecoration {
        return binding.chatsRecycler.getItemDecorationAt(index)
    }

    fun invalidateChatDecorations() {
        binding.chatsRecycler.invalidateItemDecorations()
    }

    private fun setUpRecyclerView() {
        binding.chatsRecycler.adapter = chatsAdapter

        val scrollListener = object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                when(newState) {
                    RecyclerView.SCROLL_STATE_DRAGGING ->
                        generalViewListener?.onScrollStateChanged(ScrollStates.SCROLL_STATE_DRAGGING)
                    RecyclerView.SCROLL_STATE_IDLE ->
                        generalViewListener?.onScrollStateChanged(ScrollStates.SCROLL_STATE_IDLE)
                    RecyclerView.SCROLL_STATE_SETTLING ->
                        generalViewListener?.onScrollStateChanged(ScrollStates.SCROLL_STATE_SETTLING)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                generalViewListener?.onScrolled(dx, dy)
            }

        }
        binding.chatsRecycler.addOnScrollListener(scrollListener)
    }

    private fun applyGeneralChatModel(model: GeneralChatModel) {
        val chats = model.chats
        chatsAdapter.items = chats
        GeneralChatDelegates.allChats = chats
    }

    private fun applyGeneralChatStyleOptions(options: GeneralChatStyleOptions) = with(binding) {
        val topDecorator = TopExtraSpaceDecorator(
            extraSpaceDp = options.layoutTopExtraSpaceDp
        )
        val bottomDecorator = BottomExtraSpaceDecorator(
            extraSpaceDp = options.layoutBottomExtraSpaceDp
        )

        binding.chatsRecycler.addItemDecoration(topDecorator)
        binding.chatsRecycler.addItemDecoration(bottomDecorator)
        binding.layoutBackground.setBackgroundColor(options.layoutBackgroundColor)
        binding.chatsRecycler.setMarginsDp(
            left = options.layoutStartExtraSpaceDp,
            right = options.layoutEndExtraSpaceDp,
        )
    }

    private fun applyChatStyleOptions(options: ChatStyleOptions) {
        chatsAdapter = GeneralChatAdapter(chatKitFactory, options)
        chatsAdapter.items = generalChatModel.chats
        binding.chatsRecycler.adapter = chatsAdapter
    }


}