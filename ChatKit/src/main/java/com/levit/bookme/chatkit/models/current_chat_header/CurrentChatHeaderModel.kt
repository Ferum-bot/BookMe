package com.levit.bookme.chatkit.models.current_chat_header

interface CurrentChatHeaderModel {

    /**
     * The name of current interlocutor.
     */
    val interlocutorName: String

    /**
     * The additional text under interlocutor name.
     * If null, the additional text view will bt Invisible.
     */
    val additionalText: String?

    /**
     * The link to interlocutor profile image.
     * Will be loaded and cached through Glide.
     */
    val profileIconImageUrl: String

}