package com.levit.book_me.ui.fragments.creating_profile.utills

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.core.ui.custom_view.BigBookItemView
import com.levit.book_me.network.models.google_books.GoogleBook

class CreatingBooksAdapter(
    private val listener: CreatingBooksClickListener
): ListAdapter<GoogleBook, CreatingBooksAdapter.CreatingBooksViewHolder>(CALL_BACK) {

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

    interface CreatingBooksClickListener {

        fun onBookClicked(newState: CreatingBooksStates, book: GoogleBook)
    }

    enum class CreatingBooksStates(val boolean: Boolean) {
        CHOSEN(true), NOT_CHOSEN(false);
    }

    class CreatingBooksViewHolder private constructor(
        private val bookView: BigBookItemView,
        private val listener: CreatingBooksClickListener
    ): RecyclerView.ViewHolder(bookView) {

        companion object {

            fun getFrom(parent: ViewGroup, listener: CreatingBooksClickListener): CreatingBooksViewHolder {
                val context = parent.context
                val layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                val view = BigBookItemView(context).apply {
                    setLayoutParams(layoutParams)
                }
                return CreatingBooksViewHolder(view, listener)
            }
        }

        private lateinit var currentBook: GoogleBook

        private var currentState: CreatingBooksStates = CreatingBooksStates.NOT_CHOSEN

        init {

            bookView.setClickListener(View.OnClickListener {
                currentState = if (currentState == CreatingBooksStates.NOT_CHOSEN) {
                    CreatingBooksStates.CHOSEN
                } else {
                    CreatingBooksStates.NOT_CHOSEN
                }
                currentBook.isChosen = currentState.boolean
                bookView.setChosen(currentState.boolean)

                listener::onBookClicked.invoke(currentState, currentBook)
            })
        }

        fun bind(book: GoogleBook) {
            currentBook = book
            currentState = if (book.isChosen) {
                CreatingBooksStates.CHOSEN
            }
            else {
                CreatingBooksStates.NOT_CHOSEN
            }

            bookView.setBook(currentBook)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatingBooksViewHolder {
        return CreatingBooksViewHolder.getFrom(parent, listener)
    }

    override fun onBindViewHolder(holder: CreatingBooksViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }
}