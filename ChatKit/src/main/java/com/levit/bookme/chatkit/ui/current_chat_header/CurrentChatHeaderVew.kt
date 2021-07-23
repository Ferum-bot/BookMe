package com.levit.bookme.chatkit.ui.current_chat_header

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.levit.book_me.chat_kit.databinding.CurrentChatHeaderLayoutBinding
import com.levit.bookme.chatkit.drawables.RoundedRectDrawable
import com.levit.bookme.chatkit.extensions.*
import com.levit.bookme.chatkit.extensions.defaultGlideOptions
import com.levit.bookme.chatkit.extensions.inflater
import com.levit.bookme.chatkit.extensions.provideEmptyModel
import com.levit.bookme.chatkit.extensions.setMarginsDp
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment
import com.levit.bookme.chatkit.models.utills.RemoteImageLoader

@Suppress("JoinDeclarationAndAssignment")
class CurrentChatHeaderVew @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    var chatHeaderModel = provideEmptyModel()
        set(value) {
            field = value
            applyCurrentChatHeaderModel(value)
        }

    var styleOptions = CurrentChatHeaderStyleOptions.provideDefaultOptions()
        set(value) {
            field = value
            applyCurrentChatHeaderStyleOptions(value)
        }

    var listener: CurrentChatHeaderListener? = null

    private val binding: CurrentChatHeaderLayoutBinding

    private val imageLoader: RemoteImageLoader

    init {
        binding = CurrentChatHeaderLayoutBinding.inflate(inflater, this, true)

        imageLoader = RemoteImageLoader(binding.profileIcon, defaultGlideOptions())

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
        binding.interlocutorName.text = model.interlocutorName
        binding.additionalTextField.text = model.additionalText
        imageLoader.load(model.profileIconImageUrl)
    }

    private fun applyCurrentChatHeaderStyleOptions(options: CurrentChatHeaderStyleOptions) {
        applyBackButtonOptions(options)
        applyProfileIconOptions(options)
        applyInterlocutorNameOptions(options)
        applyAdditionalTextOptions(options)
        applyGeneralOptions(options)
    }

    private fun applyBackButtonOptions(options: CurrentChatHeaderStyleOptions) = with(binding) {
        backButton.setMarginsDp(
            left = options.backButtonMarginStartDp,
            top = options.backButtonMarginTopDp,
            right = options.backButtonMarginEndDp,
            bottom = options.backButtonMarginBottomDp,
        )
    }

    private fun applyProfileIconOptions(options: CurrentChatHeaderStyleOptions) = with(binding) {
        profileIcon.setMarginsDp(
            left = options.profileIconMarginStartDp,
            top = options.profileIconMarginTopDp,
            right = options.profileIconMarginEndDp,
            bottom = options.profileIconMarginBottomDp,
        )
    }

    private fun applyInterlocutorNameOptions(options: CurrentChatHeaderStyleOptions) = with(binding) {
        interlocutorName.setTextColor(options.interlocutorNameTextColor)
        interlocutorName.setTextSizeSp(options.interlocutorNameTextSizeSp)
        interlocutorName.setMarginsDp(
            left = options.interlocutorNameMarginStartDp,
            top = options.interlocutorNameMarginTopDp,
            right = options.interlocutorNameMarginEndDp,
            bottom = options.interlocutorNameMarginBottomDp,
        )

        when(options.interlocutorNameAlignment) {
            MessageTextAlignment.START ->
                alignInterlocutorNameToStart()
            MessageTextAlignment.END ->
                alignInterlocutorNameToEnd()
        }
    }

    private fun alignInterlocutorNameToStart() {
        binding.interlocutorName.gravity = Gravity.START
    }

    private fun alignInterlocutorNameToEnd() {
        binding.interlocutorName.gravity = Gravity.END
    }

    private fun applyAdditionalTextOptions(options: CurrentChatHeaderStyleOptions) = with(binding) {
        additionalTextField.isVisible = options.showAdditionalText
        additionalTextField.setTextColor(options.additionalTextColor)
        additionalTextField.setTextSizeSp(styleOptions.additionalTextSizeSp)
        additionalTextField.setMarginsDp(
            left = options.additionalTextMarginStartDp,
            top = options.additionalTextMarginTopDp,
            right = options.additionalTextMarginEndDp,
            bottom = options.additionalTextMarginBottomDp,
        )

        when(options.additionalTextAlignment) {
            MessageTextAlignment.START ->
                alignAdditionalTextToStart()
            MessageTextAlignment.END ->
                alignAdditionalTextToEnd()
        }
    }

    private fun alignAdditionalTextToStart() {
        binding.additionalTextField.gravity = Gravity.START
    }

    private fun alignAdditionalTextToEnd() {
        binding.additionalTextField.gravity = Gravity.END
    }

    private fun applyGeneralOptions(options: CurrentChatHeaderStyleOptions) = with(binding) {
        val layoutDrawable = RoundedRectDrawable(
            backgroundColor = options.layoutBackgroundColor,
            strokeColor = options.layoutBackgroundStrokeColor,
            strokeWidthPx = dpToPx(options.layoutBackgroundStrokeWidthDp) ?: 0,
            radiusTopRightPx = dpToPx(options.layoutBackgroundTopRightRadiusDp) ?: 0,
            radiusTopLeftPx = dpToPx(options.layoutBackgroundTopLeftRadiusDp) ?: 0,
            radiusBottomRightPx = dpToPx(options.layoutBackgroundBottomRightRadiusDp) ?: 0,
            radiusBottomLeftPx = dpToPx(options.layoutBackgroundBottomLeftRadiusDp) ?: 0,
        )

        layoutBackground.background = layoutDrawable
    }
}