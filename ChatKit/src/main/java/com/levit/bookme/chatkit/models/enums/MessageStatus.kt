package com.levit.bookme.chatkit.models.enums

enum class MessageStatus {

    /**
     * Shows that current message in the feed
     * is sending. Actually, near the message will be loader.
     */
    SENDING,

    /**
     * Shows that current message in the feed is sent.
     * Actually, near the message will be special sign.
     */
    SENT,

    /**
     * Shows that current message in the feed is received.
     * Actually, near the message will be special sign.
     */
    RECEIVED,

    /**
     * Shows that current message in the feed does not sent.
     * Actually, near the message will be special sign.
     */
    ERROR;

}