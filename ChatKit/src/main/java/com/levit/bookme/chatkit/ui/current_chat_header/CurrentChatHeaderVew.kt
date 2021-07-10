package com.levit.bookme.chatkit.ui.current_chat_header

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.chat_kit.databinding.CurrentChatHeaderLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel

@Suppress("JoinDeclarationAndAssignment")
class CurrentChatHeaderVew @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    var chatHeaderModel: CurrentChatHeaderModel = provideEmptyModel()
        set(value) {
            field = value
            applyCurrentChatHeaderModel(value)
        }

    var listener: CurrentChatHeaderListener? = null

    private val binding: CurrentChatHeaderLayoutBinding

    init {
        binding = CurrentChatHeaderLayoutBinding.inflate(inflater, this, true)

        setAllClickListeners()
    }

    private fun setAllClickListeners() {
        binding.backButton.setOnClickListener {
            listener?.onBackButtonClicked(chatHeaderModel)
        }

        binding.profileIcon.setOnClickListener {
            listener?.onProfileIconClicked(chatHeaderModel)
        }

        binding.interlocutorName.setOnClickListener {
            listener?.onInterlocutorNameClicked(chatHeaderModel)
        }

        binding.additionalTextField.setOnClickListener {
            listener?.onAdditionalTextClicked(chatHeaderModel)
        }
    }

    private fun applyCurrentChatHeaderModel(model: CurrentChatHeaderModel) {

    }
}