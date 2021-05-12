package com.levit.book_me.ui.fragments.creating_profile.utills

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CreatingBooksOffsetDecorator: RecyclerView.ItemDecoration() {

    companion object {
        private const val MARGIN = 16
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = MARGIN
        outRect.top = MARGIN
        outRect.left = MARGIN
        outRect.right = MARGIN
    }
}