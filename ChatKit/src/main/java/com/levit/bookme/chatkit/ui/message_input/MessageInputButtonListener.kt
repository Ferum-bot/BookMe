package com.levit.bookme.chatkit.ui.message_input

/**
 * This interface used to handle send button click.
 *
 */
interface MessageInputButtonListener {

    /**
     * If this method returns true, the current input edit text
     * will be empty, else the text is saved.
     */
    fun onSendClicked(messageText: String?): Boolean

}