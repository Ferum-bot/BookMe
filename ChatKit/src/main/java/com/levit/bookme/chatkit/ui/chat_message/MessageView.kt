package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.levit.book_me.chat_kit.R
import com.levit.bookme.chatkit.extensions.emptyMessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.utills.RemoteImageLoader

internal abstract class MessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    var styleOptions = MessageStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyStyleOptions(value)
        }

    var messageModel = emptyMessageModel()
        set(value) {
            field = value
            applyMessageModel(value)
        }

    var listener: MessageListener? = null

    abstract var isFirstMessage: Boolean

    protected abstract val binding: ViewBinding

    protected abstract val profileImageLoader: RemoteImageLoader

    protected val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    protected abstract fun applyStyleOptions(options: MessageStyleOptions)

    protected abstract fun applyMessageModel(model: MessageModel)

    protected abstract fun setAllClickListeners()

    protected fun defaultGlideOptions() = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.default_image_placeholder)
        .error(R.drawable.ic_default_error_placeholder)
}