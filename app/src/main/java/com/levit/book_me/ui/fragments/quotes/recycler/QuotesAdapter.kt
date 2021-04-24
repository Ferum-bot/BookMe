package com.levit.book_me.ui.fragments.quotes.recycler

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.R
import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.core.ui.custom_view.QuoteItemView
import timber.log.Timber

class QuotesAdapter(
    private val listener: QuoteStatusListener?
): ListAdapter<GoQuote, QuotesAdapter.QuoteViewHolder>(CALL_BACK) {

    companion object {
        private val CALL_BACK = object: DiffUtil.ItemCallback<GoQuote>() {

            override fun areItemsTheSame(oldItem: GoQuote, newItem: GoQuote): Boolean {
                return oldItem.authorFullName.hashCode() == newItem.authorFullName.hashCode()
                        && oldItem.tag.hashCode() == newItem.tag.hashCode()
                        && oldItem.text.hashCode() == newItem.text.hashCode()
            }

            override fun areContentsTheSame(oldItem: GoQuote, newItem: GoQuote): Boolean {
                return oldItem == newItem
            }
        }
    }

    enum class QuoteStatuses(val bool: Boolean) {
        CHOSEN(true), NOT_CHOSEN(false);

        companion object {

            fun getFrom(bool: Boolean) = if(bool) {
                CHOSEN
            } else {
                NOT_CHOSEN
            }
        }
    }

    interface QuoteStatusListener {

        fun onQuoteStatusChanged(status: QuoteStatuses, quote: GoQuote)
    }

    class QuoteViewHolder private constructor(
        private val view: QuoteItemView,
        private val adapter: QuotesAdapter,
    ): RecyclerView.ViewHolder(view) {

        companion object {

            fun getFrom(parent: ViewGroup, adapter: QuotesAdapter): QuoteViewHolder {
                val view = QuoteItemView(parent.context).apply {
                    val layoutParams = ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    )
                    setLayoutParams(layoutParams)
                }

                return QuoteViewHolder(view, adapter)
            }

        }

        private var currentStatus = QuoteStatuses.NOT_CHOSEN

        private lateinit var quote: GoQuote

        init {
            view.setOnClickListener {
                when(currentStatus) {
                    QuoteStatuses.CHOSEN -> {
                        currentStatus = QuoteStatuses.NOT_CHOSEN
                        quote.isChosen = false
                        adapter.chosenQuote = null
                        view.setChosen(currentStatus.bool)
                    }
                    QuoteStatuses.NOT_CHOSEN -> {
                        currentStatus = QuoteStatuses.CHOSEN
                        quote.isChosen = true
                        view.setChosen(currentStatus.bool)
                        adapter.chosenQuote?.setNotChosen()
                        adapter.chosenQuote = this
                    }
                }
                adapter.listener?.onQuoteStatusChanged(currentStatus, quote)
            }
        }

        fun bind(quote: GoQuote) {
            this.quote = quote
            view.setQuote(quote)
            view.setChosen(quote.isChosen)
            currentStatus = QuoteStatuses.getFrom(quote.isChosen)
        }

        private fun setNotChosen() {
            this.quote.isChosen = false
            view.setChosen(false)
            currentStatus = QuoteStatuses.NOT_CHOSEN
            adapter.listener?.onQuoteStatusChanged(currentStatus, quote)
        }
    }

    private var chosenQuote: QuoteViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder.getFrom(parent, this)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        Timber.tag("BindViewHolder").i("Position: $position")
        val quote = getItem(position)
        holder.bind(quote)
    }
}