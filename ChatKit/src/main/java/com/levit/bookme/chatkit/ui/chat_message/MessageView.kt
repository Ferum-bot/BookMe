package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.levit.bookme.chatkit.extensions.emptyMessageModel
import com.levit.bookme.chatkit.models.MessageStyleOptions
import com.levit.bookme.chatkit.models.interfaces.MessageModel

abstract class MessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    var styleOptions: MessageStyleOptions = MessageStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyStyleOptions(value)
        }

    var messageModel: MessageModel = emptyMessageModel()
        set(value) {
            field = value
            applyMessageModel(value)
        }

    abstract var isFirstMessage: Boolean

    protected abstract val binding: ViewBinding

    protected val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    protected abstract fun applyStyleOptions(options: MessageStyleOptions)
    protected abstract fun applyMessageModel(model: MessageModel)


}