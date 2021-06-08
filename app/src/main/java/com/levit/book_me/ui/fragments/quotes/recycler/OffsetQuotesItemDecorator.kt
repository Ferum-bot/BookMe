package com.levit.book_me.ui.fragments.quotes.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetQuotesItemDecorator: RecyclerView.ItemDecoration() {

    companion object {
        private const val BOTTOM_MARGIN = 16
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = BOTTOM_MARGIN
    }
}