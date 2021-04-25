package com.levit.book_me.ui.fragments.quotes.recycler

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.core.models.GoQuotesTag
import com.levit.book_me.core.ui.custom_view.QuoteAuthorTagItemView

class QuotesTagAdapter(
    private val listener: TagClickListener,
): ListAdapter<GoQuotesTag, QuotesTagAdapter.TagViewHolder>(CALL_BACK) {

    companion object {
        private val CALL_BACK = object: DiffUtil.ItemCallback<GoQuotesTag>() {

            override fun areItemsTheSame(oldItem: GoQuotesTag, newItem: GoQuotesTag): Boolean {
                return oldItem.name.hashCode() == newItem.name.hashCode() &&
                        oldItem.numberOfQuotes == newItem.numberOfQuotes
            }

            override fun areContentsTheSame(oldItem: GoQuotesTag, newItem: GoQuotesTag): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface TagClickListener {

        fun onTagClicked(tag: GoQuotesTag)
    }

    class TagViewHolder private constructor(
        private val view: QuoteAuthorTagItemView,
        private val listener: TagClickListener
    ): RecyclerView.ViewHolder(view) {

        companion object {

            fun getFrom(parent: ViewGroup, listener: TagClickListener): TagViewHolder {
                val view = QuoteAuthorTagItemView(parent.context).apply {
                    val layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    )
                    setLayoutParams(layoutParams)
                }
                return TagViewHolder(view, listener)
            }
        }

        init {
            view.setNextClickListener(View.OnClickListener {
                listener::onTagClicked.invoke(currentTag)
            })
        }

        private lateinit var currentTag: GoQuotesTag

        fun bind(tag: GoQuotesTag) {
            currentTag = tag
            view.setTag(tag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder.getFrom(parent, listener)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val tag = getItem(position)
        holder.bind(tag)
    }
}