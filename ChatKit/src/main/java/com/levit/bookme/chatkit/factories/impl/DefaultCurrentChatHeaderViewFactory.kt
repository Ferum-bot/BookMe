package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew

internal class DefaultCurrentChatHeaderViewFactory {

    private val layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
    )

    fun createCurrentChatHeader(
        model: CurrentChatHeaderModel?, requireContext: () -> Context,
        styleOptions: CurrentChatHeaderStyleOptions,
    ): CurrentChatHeaderVew {
        if (model == null) {
            return CurrentChatHeaderVew(requireContext.invoke()).apply {
                this.layoutParams = this@DefaultCurrentChatHeaderViewFactory.layoutParams
                this.styleOptions = styleOptions
            }
        }

        return CurrentChatHeaderVew(requireContext.invoke()).apply {
            this.styleOptions = styleOptions
            chatHeaderModel = model
        }
    }

    fun bindCurrentChatHeader(
        view: CurrentChatHeaderVew, model: CurrentChatHeaderModel,
        styleOptions: CurrentChatHeaderStyleOptions
    ) {
        view.styleOptions = styleOptions
        view.chatHeaderModel = model
    }
}