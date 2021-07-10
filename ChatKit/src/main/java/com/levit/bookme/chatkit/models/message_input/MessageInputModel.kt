package com.levit.bookme.chatkit.models.message_input

interface MessageInputModel {

    /**
     * This string will set as a hint to empty input edit text.
     * If null, the hint will be: "Enter your message..."
     */
    val hintString: String?

    /**
     * The start message of input edit text.
     * If null, it will be empty.
     */
    val initialMessage: String?

}