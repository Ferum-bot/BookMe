package com.levit.book_me.ui.fragments.main_screens.chats

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentMainScreenChatsBinding
import com.levit.book_me.ui.base.BaseMainScreenFragment
import com.levit.book_me.ui.chat_kit_options.provideDefaultChatOptions
import com.levit.book_me.ui.chat_kit_options.provideDefaultGeneralOptions
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel

class MainScreenChatsFragment:
    BaseMainScreenFragment<MainScreenChatsViewModel>(R.layout.fragment_main_screen_chats) {

    override val viewModel: MainScreenChatsViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    private val binding: FragmentMainScreenChatsBinding by viewBinding {
        FragmentMainScreenChatsBinding.bind(it)
    }

    /**
     * Style options for ChatKit.
     */
    private val generalChatOptions = provideDefaultGeneralOptions()
    private val chatOptions = provideDefaultChatOptions()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainScreenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        configureLayout()
        configureChatKit()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.chats.observe(viewLifecycleOwner) { chats ->
            val generalModel = object: GeneralChatModel {
                override val chats: List<ChatModel> = chats
            }

            binding.chatsView.generalChatModel = generalModel
        }

        viewModel.currentStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                MainScreenChatsViewModel.Status.LOADING -> {
                    binding.chatsView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                    binding.infoText.visibility = View.GONE
                }
                MainScreenChatsViewModel.Status.LOADED_FROM_REMOTE -> {
                    binding.chatsView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.infoText.visibility = View.GONE
                }
                MainScreenChatsViewModel.Status.LOADED_FROM_CACHE -> {
                    binding.chatsView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.infoText.visibility = View.GONE
                }
                MainScreenChatsViewModel.Status.NO_AVAILABLE_CHATS -> {
                    binding.chatsView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.GONE
                    binding.infoText.visibility = View.VISIBLE
                }
                MainScreenChatsViewModel.Status.NO_AVAILABLE_DATA, null -> {
                    binding.chatsView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.errorText.visibility = View.VISIBLE
                    binding.infoText.visibility = View.GONE
                }
            }
        }

    }

    private fun configureLayout() {
        binding.errorText.addClickableText(R.string.try_again) {
            viewModel.refreshChats()
        }
    }

    private fun configureChatKit() {
        binding.chatsView.apply {
            generalStyleOptions = generalChatOptions
            chatStyleOptions = chatOptions
        }
    }
}