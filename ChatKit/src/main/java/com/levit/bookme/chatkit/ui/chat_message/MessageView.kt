package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.levit.bookme.chatkit.models.MessageStyleOptions

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

    protected val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    protected abstract val binding: ViewBinding

    protected abstract fun applyStyleOptions(options: MessageStyleOptions)
}