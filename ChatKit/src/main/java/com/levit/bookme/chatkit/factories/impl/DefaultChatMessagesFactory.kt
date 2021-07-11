package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.enums.MessageType
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.ui.chat_message.InterlocutorMessageView
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.chat_message.YourMessageView
import java.util.*

internal class DefaultChatMessagesFactory {

    companion object {

        private const val MESSAGE_VIEW_WIDTH = ConstraintLayout.LayoutParams.MATCH_PARENT
        private const val MESSAGE_VIEW_HEIGHT = ConstraintLayout.LayoutParams.WRAP_CONTENT
    }

    private val layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
    )

    fun createMessageFrom(
        position: Int, allMessages: List<MessageModel>, requireContext: () -> Context,
        yourMessageOptions: MessageStyleOptions,
        interlocutorMessageOptions: MessageStyleOptions
    ): MessageView {
        if (allMessages.notContainsPosition(position)) {
            throwInvalidPosition(position, allMessages.size)
        }

        val modelToCreate = allMessages[position]
        return when(modelToCreate.type) {
            MessageType.YOUR_MESSAGE ->
                createYourMessage(
                    messageModel = modelToCreate,
                    previousModel = allMessages.getOrNull(position - 1),
                    requireContext, yourMessageOptions
                )
            MessageType.INTERLOCUTOR_MESSAGE ->
                createInterlocutorMessage(
                    messageModel = modelToCreate,
                    previousModel = allMessages.getOrNull(position - 1),
                    requireContext, interlocutorMessageOptions
                )
        }
    }

    fun createYourMessageFrom(
        position: Int?, allMessages: List<MessageModel>?, requireContext: () -> Context,
        yourMessageOptions: MessageStyleOptions,
    ): YourMessageView {
        if (position == null || allMessages == null) {
            return YourMessageView(requireContext.invoke()).apply {
                layoutParams = this@DefaultChatMessagesFactory.layoutParams
                styleOptions = yourMessageOptions
            }
        }

        if (allMessages.notContainsPosition(position)) {
            throwInvalidPosition(position, allMessages.size)
        }
        val modelToCreate = allMessages[position]
        if (modelToCreate.type != MessageType.YOUR_MESSAGE) {
            throwInvalidMessageType(modelToCreate.type, MessageType.YOUR_MESSAGE)
        }

        return createYourMessage(
            messageModel = modelToCreate,
            previousModel = allMessages.getOrNull(position - 1),
            requireContext,
            yourMessageOptions
        )
    }

    fun createInterlocutorMessageFrom(
        position: Int?, allMessages: List<MessageModel>?, requireContext: () -> Context,
        interlocutorMessageOptions: MessageStyleOptions
    ): InterlocutorMessageView {
        if (position == null || allMessages == null) {
            return InterlocutorMessageView(requireContext.invoke()).apply {
                layoutParams = this@DefaultChatMessagesFactory.layoutParams
                styleOptions = interlocutorMessageOptions
            }
        }

        if (allMessages.notContainsPosition(position)) {
            throwInvalidPosition(position, allMessages.size)
        }
        val modelToCreate = allMessages[position]
        if (modelToCreate.type != MessageType.INTERLOCUTOR_MESSAGE) {
            throwInvalidMessageType(modelToCreate.type, MessageType.INTERLOCUTOR_MESSAGE)
        }

        return createInterlocutorMessage(
            messageModel = modelToCreate,
            previousModel = allMessages.getOrNull(position - 1),
            requireContext,
            interlocutorMessageOptions
        )
    }

    fun bindMessageFrom(
        view: MessageView, position: Int, allMessages: List<MessageModel>,
        yourMessageOptions: MessageStyleOptions,
        interlocutorMessageOptions: MessageStyleOptions
    ) {
        if (allMessages.notContainsPosition(position)) {
            throwInvalidPosition(position, allMessages.size)
        }
        val modelToBind = allMessages[position]
        checkTheMessageType(view, modelToBind.type)

        view.messageModel = modelToBind
        view.styleOptions = when(modelToBind.type) {
            MessageType.YOUR_MESSAGE -> yourMessageOptions
            MessageType.INTERLOCUTOR_MESSAGE -> interlocutorMessageOptions
        }
    }

    private fun createInterlocutorMessage(
        messageModel: MessageModel, previousModel: MessageModel?, requireContext: () -> Context,
        interlocutorMessageOptions: MessageStyleOptions
    ): InterlocutorMessageView {
        val iconIsNeeded: Boolean = previousModel == null || previousModel.type == MessageType.YOUR_MESSAGE
        return if (iconIsNeeded) {
            createInterlocutorMessageWithIcon(messageModel, requireContext, interlocutorMessageOptions)
        } else {
            createInterlocutorMessageWithoutIcon(messageModel, requireContext, interlocutorMessageOptions)
        }
    }

    private fun createYourMessage(
        messageModel: MessageModel, previousModel: MessageModel?, requireContext: () -> Context,
        yourMessageOptions: MessageStyleOptions,
    ): YourMessageView {
        val iconIsNeeded: Boolean = previousModel == null || previousModel.type == MessageType.INTERLOCUTOR_MESSAGE
        return if (iconIsNeeded) {
            createYourMessageWithIcon(messageModel, requireContext, yourMessageOptions)
        } else {
            createYourMessageWithoutIcon(messageModel, requireContext, yourMessageOptions)
        }
    }

    private fun createInterlocutorMessageWithIcon(
        messageModel: MessageModel, requireContext: () -> Context,
        interlocutorMessageOptions: MessageStyleOptions
    ): InterlocutorMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT,
        )
        return InterlocutorMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = true
            this.styleOptions = interlocutorMessageOptions
        }
    }

    private fun createInterlocutorMessageWithoutIcon(
        messageModel: MessageModel, requireContext: () -> Context,
        interlocutorMessageOptions: MessageStyleOptions
    ): InterlocutorMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT,
        )
        return InterlocutorMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = false
            this.styleOptions = interlocutorMessageOptions
        }
    }

    private fun createYourMessageWithIcon(
        messageModel: MessageModel, requireContext: () -> Context,
        yourMessageOptions: MessageStyleOptions,
    ): YourMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT
        )
        return YourMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = true
            this.styleOptions = yourMessageOptions
        }
    }

    private fun createYourMessageWithoutIcon(
        messageModel: MessageModel, requireContext: () -> Context,
        yourMessageOptions: MessageStyleOptions,
    ): YourMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT,
        )
        return YourMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = false
            this.styleOptions = yourMessageOptions
        }
    }

    private fun checkTheMessageType(view: MessageView, type: MessageType) = when(type) {
        MessageType.YOUR_MESSAGE -> {
            if (view.isNotYourMessage()) {
                throwInvalidMessageToBind(view, type)
            } else { Unit }
        }
        MessageType.INTERLOCUTOR_MESSAGE -> {
            if (view.isNotInterlocutorMessage()) {
                throwInvalidMessageToBind(view, type)
            } else { Unit }
        }
    }

    private fun throwInvalidPosition(position: Int, size: Int): Nothing {
        throw IndexOutOfBoundsException("Invalid position to create view: $position. Size: $size")
    }

    private fun throwInvalidMessageType(actual: MessageType, expected: MessageType): Nothing {
        throw InvalidPropertiesFormatException(
            "Invalid message type to create view: expected: $expected, actual: $actual"
        )
    }

    private fun throwInvalidMessageToBind(view: MessageView, bindType: MessageType): Nothing {
        throw IllegalArgumentException(
            "Invalid message type to bind: view: $view, bindType: $bindType"
        )
    }

    private fun List<MessageModel>.notContainsPosition(position: Int): Boolean {
        if (position < 0) {
            return true
        }
        if (position >= size) {
            return true
        }
        return false
    }

    private fun MessageView.isNotYourMessage(): Boolean {
        val yourMessage = this as? YourMessageView
        return yourMessage == null
    }

    private fun MessageView.isNotInterlocutorMessage(): Boolean {
        val interlocutorMessage = this as? InterlocutorMessageView
        return interlocutorMessage == null
    }
}