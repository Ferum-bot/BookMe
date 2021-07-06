package com.levit.bookme.chatkit.models.enums

enum class MessageType {

    /**
     * This flag shows, that this is current user message.
     */
    YOUR_MESSAGE,

    /**
     * This flag shows that it is interlocutor's / friend's / teammate's message.
     */
    INTERLOCUTOR_MESSAGE;
}