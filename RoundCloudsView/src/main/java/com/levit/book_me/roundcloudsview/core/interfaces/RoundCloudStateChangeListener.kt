package com.levit.book_me.roundcloudsview.core.interfaces

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.models.RoundCloud

interface RoundCloudStateChangeListener {

    fun onStateChanged(newState: RoundCloudState, cloud: RoundCloud)

}