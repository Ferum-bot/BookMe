package com.levit.bookme.chatkit.ui.general_chat

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.chat_kit.databinding.GeneralChatLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel
import com.levit.bookme.chatkit.models.general_chat.GeneralChatStyleOptions

@Suppress("JoinDeclarationAndAssignment")
class GeneralChatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    var generalChatModel: GeneralChatModel = provideEmptyModel()
        set(value) {
            field = value
            applyGeneralChatModel(value)
        }

    var styleOptions: GeneralChatStyleOptions = GeneralChatStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyGeneralChatStyleOptions(value)
        }

    var listener: GeneralChatListener? = null

    private val binding: GeneralChatLayoutBinding

    init {
        binding = GeneralChatLayoutBinding.inflate(inflater, this, true)
    }

    fun addChatDecorator(decorator: RecyclerView.ItemDecoration) {
        binding.chatsRecycler.addItemDecoration(decorator)
    }

    fun addChatDecorator(decorator: RecyclerView.ItemDecoration, index: Int) {
        binding.chatsRecycler.addItemDecoration(decorator, index)
    }

    private fun applyGeneralChatModel(model: GeneralChatModel) {

    }

    private fun applyGeneralChatStyleOptions(options: GeneralChatStyleOptions) {

    }

}