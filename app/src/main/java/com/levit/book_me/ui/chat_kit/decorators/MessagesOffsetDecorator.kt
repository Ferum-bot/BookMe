package com.levit.book_me.ui.chat_kit.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MessagesOffsetDecorator: RecyclerView.ItemDecoration() {

    companion object {
        private const val VERTICAL_MARGIN = 8
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = VERTICAL_MARGIN
        outRect.top = VERTICAL_MARGIN
    }

}