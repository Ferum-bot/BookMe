package com.levit.book_me.ui.fragments.main_screens.current_chat

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.databinding.FragmentMainScreenCurrentChatBinding
import com.levit.book_me.ui.BundleConstants
import com.levit.book_me.ui.base.BaseMainScreenFragment
import com.levit.bookme.chatkit.extensions.changeProperties
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.models.enums.MessageStatus
import com.levit.bookme.chatkit.models.enums.MessageType
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.ui.chat_message.MessageListener
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderListener
import com.levit.bookme.chatkit.ui.message_input.MessageInputButtonListener
import com.levit.bookme.chatkit.ui.message_input.MessageInputTextChangeListener

class MainScreenCurrentChatFragment:
    BaseMainScreenFragment<MainScreenCurrentChatViewModel>(R.layout.fragment_main_screen_current_chat),
    CurrentChatHeaderListener, MessageInputButtonListener, MessageInputTextChangeListener,
    MessageListener {

    private val chatId: Long by lazy {
        val args = requireArguments()
        args.getLong(BundleConstants.CURRENT_CHAT_ID_NAME, -1)
    }

    private val interlocutorId: Long by lazy {
        val args = requireArguments()
        args.getLong(BundleConstants.CURRENT_INTERLOCUTOR_ID_NAME, -1)
    }

    private val binding: FragmentMainScreenCurrentChatBinding by viewBinding {
        FragmentMainScreenCurrentChatBinding.bind(it)
    }

    override val viewModel: MainScreenCurrentChatViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainScreenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        configureLayout()
        configureChatKit()
        setUpChat()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.currentStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                MainScreenCurrentChatViewModel.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.currentChat.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                }
                MainScreenCurrentChatViewModel.Status.LOADED_FROM_CACHE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentChat.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                }
                MainScreenCurrentChatViewModel.Status.LOADED_FROM_REMOTE -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentChat.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                }
                MainScreenCurrentChatViewModel.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentChat.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                }
                MainScreenCurrentChatViewModel.Status.CONNECTION_ESTABLISHED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentChat.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    showConnectionEstablished()
                }
                MainScreenCurrentChatViewModel.Status.CONNECTION_LOST -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentChat.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    showConnectionLost()
                }
            }
        }

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            val model = object: CurrentChatFeedModel {
                override val allMessages: List<MessageModel> = messages
            }
            binding.currentChat.currentChatFeedModel = model
        }

        viewModel.interlocutor.observe(viewLifecycleOwner) { interlocutor ->
            val model = object: CurrentChatHeaderModel {
                override val interlocutorName: String = interlocutor.name
                override val profileIconImageUrl: String = interlocutor.profilePhotoUrl
                override val additionalText: String? = null
            }
            binding.currentChat.profileHeaderModel = model
        }

        viewModel.isInterlocutorOnline.observe(viewLifecycleOwner) { isOnline ->
            binding.currentChat.isInterlocutorOnline = isOnline
        }

        viewModel.isInterlocutorTyping.observe(viewLifecycleOwner) { isTyping ->
            var model = binding.currentChat.profileHeaderModel ?: return@observe
            model = model.changeProperties(
                additionalText = getString(R.string.is_typing)
            )
            binding.currentChat.profileHeaderModel = model
        }
    }

    override fun onAdditionalTextClicked(model: CurrentChatHeaderModel) {
        sharedViewModel.openInterlocutorProfile(interlocutorId)
    }

    override fun onAuthorNameClicked(model: MessageModel) {
        sharedViewModel.openInterlocutorProfile(interlocutorId)
    }

    override fun onBackButtonClicked(model: CurrentChatHeaderModel) {
        sharedViewModel.openGeneralChat()
    }

    override fun onInterlocutorNameClicked(model: CurrentChatHeaderModel) {
        sharedViewModel.openInterlocutorProfile(interlocutorId)
    }

    override fun onMessageClicked(model: MessageModel) {

    }

    override fun onMessageLongClicked(model: MessageModel) {

    }

    override fun onMessageStatusClicked(model: MessageModel) {
        if (model.messageStatus == MessageStatus.ERROR) {
            viewModel.retrySendingMessage(model as Message)
        }
    }

    override fun onProfileIconClicked(model: CurrentChatHeaderModel) {
        sharedViewModel.openInterlocutorProfile(interlocutorId)
    }

    override fun onProfileIconClicked(model: MessageModel) {
        if (model.type == MessageType.INTERLOCUTOR_MESSAGE) {
            sharedViewModel.openInterlocutorProfile(interlocutorId)
        }
    }

    override fun onProfileIconLongClicked(model: MessageModel) {

    }

    override fun onSendClicked(messageText: String?): Boolean {
        messageText ?: return false
        viewModel.sendMessage(messageText)
        return true
    }

    override fun onTextChanged(newText: String) {

    }

    private fun configureChatKit() {
        binding.currentChat.currentChatFeedStyleOptions = CurrentChatFeedStyleOptions.provideDefaultOptions()
        binding.currentChat.headerStyleOptions = CurrentChatHeaderStyleOptions.provideDefaultOptions()
        binding.currentChat.messageInputStyleOptions = MessageInputStyleOptions.provideDefaultStyleOptions()
        binding.currentChat.yourMessageStyleOptions = MessageStyleOptions.provideDefaultStyleOptions()
        binding.currentChat.interlocutorMessageStyleOptions = MessageStyleOptions.provideDefaultStyleOptions()

        binding.currentChat.messageInputButtonListener = this
        binding.currentChat.messageInputTextChangeListener = this
        binding.currentChat.messageListener = this
        binding.currentChat.chatFeedHeaderListener = this

        binding.currentChat.messageInputModel = object: MessageInputModel {
            override val initialMessage: String? = null
            override val hintString: String? = getString(R.string.hint_input_message)
        }
    }

    private fun configureLayout() {
        binding.errorText.addClickableText(R.string.try_again) {
            viewModel.getChatConnection(chatId)
        }
    }

    private fun setUpChat() {
        viewModel.getChatConnection(chatId)
    }

    private fun showConnectionEstablished() {
        showSuccessMessage(R.string.connection_restored)
    }

    private fun showConnectionLost() {
        showError(R.string.connection_lost)
    }
}