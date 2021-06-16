package com.levit.book_me.ui.fragments.utills

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.core.ui.custom_view.BigBookItemView
import com.levit.book_me.network.models.google_books.GoogleBook

class BaseBooksAdapter(
    private val listener: BaseBooksClickListener? = null,
    private val isCheckable: Boolean = true,
): ListAdapter<GoogleBook, BaseBooksAdapter.BaseBooksViewHolder>(CALL_BACK) {

    companion object {

        private val CALL_BACK = object: DiffUtil.ItemCallback<GoogleBook>() {

            override fun areItemsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
                return oldItem.title == newItem.title && oldItem.listOfAuthors == newItem.listOfAuthors
            }

            override fun areContentsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface BaseBooksClickListener {

        fun onBookClicked(newState: BaseBooksStates, book: GoogleBook)
    }

    enum class BaseBooksStates(val boolean: Boolean) {
        CHOSEN(true), NOT_CHOSEN(false);
    }

    class BaseBooksViewHolder private constructor(
        private val bookView: BigBookItemView,
        private val listener: BaseBooksClickListener?,
        private val isCheckable: Boolean,
    ): RecyclerView.ViewHolder(bookView) {

        companion object {

            fun getFrom(
                parent: ViewGroup, listener: BaseBooksClickListener?, isCheckable: Boolean
            ): BaseBooksViewHolder {
                val context = parent.context
                val layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                val view = BigBookItemView(context).apply {
                    setLayoutParams(layoutParams)
                }
                return BaseBooksViewHolder(view, listener, isCheckable)
            }
        }

        private lateinit var currentBook: GoogleBook

        private var currentState: BaseBooksStates = BaseBooksStates.NOT_CHOSEN

        init {

            bookView.setClickListener(View.OnClickListener {
                currentState = if (currentState == BaseBooksStates.NOT_CHOSEN) {
                    BaseBooksStates.CHOSEN
                } else {
                    BaseBooksStates.NOT_CHOSEN
                }
                currentBook.isChosen = currentState.boolean
                bookView.setChosen(currentState.boolean)

                listener?.onBookClicked(currentState, currentBook)
            })
        }

        fun bind(book: GoogleBook) {
            currentBook = book
            currentState = if (book.isChosen) {
                BaseBooksStates.CHOSEN
            }
            else {
                BaseBooksStates.NOT_CHOSEN
            }

            bookView.setBook(currentBook)
            bookView.isCheckable = isCheckable
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBooksViewHolder {
        return BaseBooksViewHolder.getFrom(parent, listener, isCheckable)
    }

    override fun onBindViewHolder(holder: BaseBooksViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }
}