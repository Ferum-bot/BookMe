package com.levit.bookme.chatkit.ui.general_chat

import com.levit.bookme.chatkit.models.enums.ScrollStates

open class SimpleGeneralChatListener: GeneralChatListener {

    override fun onScrollStateChanged(newState: ScrollStates) = Unit
    override fun onScrolled(xOffset: Int, yOffset: Int) = Unit

}