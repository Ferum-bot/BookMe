package com.levit.bookme.chatkit.ui.message_input

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.levit.book_me.chat_kit.R
import com.levit.book_me.chat_kit.databinding.MessageInputLayoutBinding
import com.levit.bookme.chatkit.drawables.RoundedRectDrawable
import com.levit.bookme.chatkit.extensions.*
import com.levit.bookme.chatkit.extensions.dpToPx
import com.levit.bookme.chatkit.extensions.getEmptyMessageInputModel
import com.levit.bookme.chatkit.extensions.getString
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions

@Suppress("JoinDeclarationAndAssignment")
class MessageInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    var styleOptions = MessageInputStyleOptions.provideDefaultStyleOptions()
        set(value) {
            field = value
            applyStyleOptions(value)
        }

    var messageInputModel = getEmptyMessageInputModel()
        set(value) {
            field = value
            applyMessageInputModel(value)
        }

    var textChangeListener: MessageInputTextChangeListener? = null

    var messageSendListener: MessageInputButtonListener? = null

    private val binding: MessageInputLayoutBinding

    init {
        binding = MessageInputLayoutBinding.inflate(inflater, this, true)

        setAllClickListeners()
    }


    private fun applyStyleOptions(options: MessageInputStyleOptions) {
        appleGeneralOptions(options)
        applyInputEditTextOptions(options)
        applySendButtonOptions(options)
    }

    private fun applyMessageInputModel(model: MessageInputModel) {
        val hintText = model.hintString ?: getString(R.string.enter_your_message)
        val initialText = model.initialMessage
        if (initialText.isNullOrBlank()) {
            binding.inputEditText.hint = hintText
        } else {
            binding.inputEditText.setText(initialText)
        }
    }

    private fun setAllClickListeners() {
        binding.sendButton.setOnClickListener {
            val currentInputText = binding.inputEditText.text?.toString()
            messageSendListener?.let { listener ->
                val result = listener.onSendClicked(currentInputText)
                if (result) {
                    binding.inputEditText.setText("")
                }
            }
        }

        binding.inputEditText.addTextChangedListener(
            onTextChanged = { charSequence: CharSequence?, i: Int, i1: Int, i2: Int ->
            charSequence ?: return@addTextChangedListener
            val inputText = charSequence.toString()
            textChangeListener?.onTextChanged(inputText)
        })
    }

    private fun applyInputEditTextOptions(options: MessageInputStyleOptions) = with(binding) {
        val backgroundDrawable = RoundedRectDrawable(
            backgroundColor = options.textInputBackgroundColor,
            strokeColor = options.textInputBackgroundStrokeColor,
            strokeWidthPx = dpToPx(options.textInputBackgroundStrokeWidthDp) ?: 0,
            radiusTopLeftPx = dpToPx(options.textInputBackgroundTopLeftRadiusDp) ?: 0,
            radiusTopRightPx = dpToPx(options.textInputBackgroundTopRightRadiusDp) ?: 0,
            radiusBottomRightPx = dpToPx(options.textInputBackgroundBottomRightRadiusDp) ?: 0,
            radiusBottomLeftPx = dpToPx(options.textInputBackgroundBottomLeftRadiusDp) ?: 0,
        )

        inputEditText.background = backgroundDrawable
        inputEditText.setTextColor(options.textInputColor)
        inputEditText.setHintTextColor(options.textHintInputColor)
        inputEditText.setTextSizeSp(options.textInputTextSizeSp)
        inputEditText.setMarginsDp(
            left = options.textInputMarginStartDp,
            top = options.textInputMarginTopDp,
            right = options.textInputMarginEndDp,
            bottom = options.textInputMarginBottomDp,
        )
        inputEditText.setPaddingDp(
            left = options.textInputPaddingStartDp,
            top = options.textInputPaddingTopDp,
            right = options.textInputPaddingEndDp,
            bottom = options.textInputPaddingBottomDp,
        )
    }

    private fun applySendButtonOptions(options: MessageInputStyleOptions) = with(binding) {
        sendButton.setMarginsDp(
            left = options.sendButtonMarginStartDp,
            top = options.sendButtonMarginTopDp,
            right = options.sendButtonMarginEndDp,
            bottom = options.sendButtonMarginBottomDp,
        )
    }

    private fun appleGeneralOptions(options: MessageInputStyleOptions) = with(binding) {
        val backgroundDrawable = RoundedRectDrawable(
            backgroundColor = options.layoutBackgroundColor,
            strokeColor = options.layoutBackgroundStrokeColor,
            strokeWidthPx = dpToPx(options.layoutBackgroundStrokeWidthDp) ?: 0,
            radiusTopLeftPx = dpToPx(options.layoutBackgroundTopLeftRadiusDp) ?: 0,
            radiusTopRightPx = dpToPx(options.layoutBackgroundTopRightRadiusDp) ?: 0,
            radiusBottomLeftPx = dpToPx(options.layoutBackgroundBottomLeftRadiusDp) ?: 0,
            radiusBottomRightPx = dpToPx(options.layoutBackgroundBottomRightRadiusDp) ?: 0,
        )

        layoutBackground.background = backgroundDrawable
    }
}