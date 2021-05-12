package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.R
import com.levit.book_me.core.extensions.defaultGlideOptions
import com.levit.book_me.core.utill.RemoteImageLoader
import com.levit.book_me.databinding.SearchBookItemLayoutBinding
import com.levit.book_me.network.models.google_books.GoogleBook

class BigBookItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchBookItemLayoutBinding

    private val inflater
    get() = LayoutInflater.from(context)

    private val imageLoader: RemoteImageLoader

    init {
        binding = SearchBookItemLayoutBinding.inflate(inflater, this, true)

        imageLoader = RemoteImageLoader(
            imageView = binding.bookImage,
            options = defaultGlideOptions()
        )
    }

    fun setClickListener(listener: OnClickListener?) {
        binding.checkbox.setOnClickListener(listener)
        binding.bookImage.setOnClickListener(listener)
        binding.bookTitle.setOnClickListener(listener)
        binding.bookAuthor.setOnClickListener(listener)
    }

    fun setBook(book: GoogleBook) {
        binding.bookAuthor.text = book.bookCategories?.parseListOfAuthors()
        binding.bookTitle.text = book.title

        if (book.isChosen) {
            binding.checkbox.setImageResource(R.drawable.ic_white_circle_full)
        } else {
            binding.checkbox.setImageResource(R.drawable.ic_white_circle_empty)
        }

        imageLoader.load(book.imageLinks)
    }

    fun setChosen(chosen: Boolean) {
        if (chosen) {
            binding.checkbox.setImageResource(R.drawable.ic_white_circle_full)
        } else {
            binding.checkbox.setImageResource(R.drawable.ic_white_circle_empty)
        }
    }

    private fun List<String>.parseListOfAuthors(): String {
        return this.joinToString()
    }
}