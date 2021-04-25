package com.levit.book_me.ui.fragments.quotes.recycler

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.core.models.GoQuotesAuthor
import com.levit.book_me.core.ui.custom_view.QuoteAuthorTagItemView

class QuotesAuthorAdapter(
    private val listener: AuthorClickListener
): ListAdapter<GoQuotesAuthor, QuotesAuthorAdapter.AuthorViewHolder>(CALL_BACK) {

    companion object {
        private val CALL_BACK = object: DiffUtil.ItemCallback<GoQuotesAuthor>() {

            override fun areItemsTheSame(oldItem: GoQuotesAuthor, newItem: GoQuotesAuthor): Boolean {
                return oldItem.fullName.hashCode() == newItem.fullName.hashCode() &&
                        oldItem.numberOfQuotes == newItem.numberOfQuotes
            }

            override fun areContentsTheSame(oldItem: GoQuotesAuthor, newItem: GoQuotesAuthor): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface AuthorClickListener {

        fun onAuthorClicked(author: GoQuotesAuthor)
    }

    class AuthorViewHolder private constructor(
            private val view: QuoteAuthorTagItemView,
            private val listener: AuthorClickListener,
    ): RecyclerView.ViewHolder(view) {

        companion object {
            fun getFrom(parent: ViewGroup, listener: AuthorClickListener): AuthorViewHolder {
                val view = QuoteAuthorTagItemView(parent.context).apply {
                    val layoutParams = ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    )
                    setLayoutParams(layoutParams)
                }
                return AuthorViewHolder(view, listener)
            }
        }

        private lateinit var currentAuthor: GoQuotesAuthor

        init {
            view.setNextClickListener(View.OnClickListener {
                listener::onAuthorClicked.invoke(currentAuthor)
            })
        }

        fun bind(author: GoQuotesAuthor) {
            currentAuthor = author
            view.setAuthor(author)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        return AuthorViewHolder.getFrom(parent, listener)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val author = getItem(position)
        holder.bind(author)
    }
}