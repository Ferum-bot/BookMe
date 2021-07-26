package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel

/**
 * If null, properties will not changed.
 */
fun CurrentChatHeaderModel.changeProperties(
    interlocutorName: String? = null,
    additionalText: String? = null,
    profileImageUrl: String? = null,
): CurrentChatHeaderModel {
    val newName = interlocutorName ?: this.interlocutorName
    val newAdditionalText = additionalText ?: this.additionalText
    val newUrl = profileImageUrl ?: this.profileIconImageUrl

    return object: CurrentChatHeaderModel {
        override val interlocutorName: String = newName
        override val additionalText: String? = newAdditionalText
        override val profileIconImageUrl: String = newUrl
    }
}