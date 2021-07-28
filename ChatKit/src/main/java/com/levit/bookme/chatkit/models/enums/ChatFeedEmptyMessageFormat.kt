package com.levit.bookme.chatkit.models.enums

/**
 * This enum provides the opportunity to customize
 * the views that will be shown if current chat has
 * no messages.
 */
enum class ChatFeedEmptyMessageFormat {

    /**
     * Only text in center will be shown.
     */
    SHOW_ONLY_TEXT,

    /**
     * Only image in center will be shown.
     */
    SHOW_ONLY_IMAGE,

    /**
     * The image and text will be shown.
     * Text will be under the image.
     */
    SHOW_TEXT_AND_IMAGE,

    /**
     * Nothing will be shown.
     */
    SHOW_NOTHING;
}
