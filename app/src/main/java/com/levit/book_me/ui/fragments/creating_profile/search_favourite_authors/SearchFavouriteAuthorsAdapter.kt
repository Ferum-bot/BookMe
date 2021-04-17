package com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.SearchFavouriteAuthorsItemLayoutBinding

class SearchFavouriteAuthorsAdapter:
    ListAdapter<Author, SearchFavouriteAuthorsAdapter.SearchFavouriteAuthorsViewHolder>(CALL_BACK) {

    companion object {
        private  val CALL_BACK = object: DiffUtil.ItemCallback<Author>() {

            override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
                return oldItem == newItem
            }

        }
    }

    class SearchFavouriteAuthorsViewHolder private constructor(
        private val binding: SearchFavouriteAuthorsItemLayoutBinding
    ): RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun getFrom(parent: ViewGroup): SearchFavouriteAuthorsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = SearchFavouriteAuthorsItemLayoutBinding.inflate(inflater, parent, false)
                return SearchFavouriteAuthorsViewHolder(binding)
            }
        }

        fun bind(author: Author) {
            binding.fullName.text = author.fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFavouriteAuthorsViewHolder {
        return SearchFavouriteAuthorsViewHolder.getFrom(parent)
    }

    override fun onBindViewHolder(holder: SearchFavouriteAuthorsViewHolder, position: Int) {
        val author = getItem(position)
        holder.bind(author)
    }


}