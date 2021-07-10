package com.levit.bookme.chatkit.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.levit.bookme.chatkit.extensions.dpToPx

internal class BottomExtraSpaceDecorator(
    private val extraSpaceDp: Int = 0,
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val paddingPx = view.dpToPx(extraSpaceDp) ?: return
        val itemsCount = parent.adapter?.itemCount ?: return
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        if (position == itemsCount - 1) {
            outRect.bottom = paddingPx
        }
    }

}