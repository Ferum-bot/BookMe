package com.levit.book_me.ui.fragments.creating_profile.search_favourites_authors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.SearchFavouriteAuthorsItemLayoutBinding

class SearchFavouriteAuthorsAdapter(
   private val stateListener: AuthorStateListener
): ListAdapter<Author, SearchFavouriteAuthorsAdapter.SearchFavouriteAuthorsViewHolder>(CALL_BACK) {

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

    interface AuthorStateListener {

        fun onAuthorStateChanged(author: Author, state: AuthorState)
    }

    enum class AuthorState {
        CHOSEN, NOT_CHOSEN
    }

    class SearchFavouriteAuthorsViewHolder private constructor(
        private val binding: SearchFavouriteAuthorsItemLayoutBinding,
        private val stateListener: AuthorStateListener,
        private val adapter: SearchFavouriteAuthorsAdapter,
    ): RecyclerView.ViewHolder(binding.root) {

        companion object{

            fun getFrom(parent: ViewGroup, stateListener: AuthorStateListener, adapter: SearchFavouriteAuthorsAdapter)
                    : SearchFavouriteAuthorsViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = SearchFavouriteAuthorsItemLayoutBinding.inflate(inflater, parent, false)
                return SearchFavouriteAuthorsViewHolder(binding, stateListener, adapter)
            }
        }

        private lateinit var author: Author

        private var currentState: AuthorState = AuthorState.NOT_CHOSEN

        init {
            binding.root.setOnClickListener { root->
                changeStateToOpposite()
                stateListener::onAuthorStateChanged.invoke(author, currentState)
            }
        }

        fun bind(author: Author) {
            this.author = author
            binding.fullName.text = author.fullName
        }

        private fun changeStateToOpposite() = with(binding.root) {
            if (currentState == AuthorState.CHOSEN) {
                currentState = AuthorState.NOT_CHOSEN
                adapter.currentChosenAuthor = null
                setBackgroundResource(R.drawable.bg_search_authors_not_chosen_item)
            } else {
                currentState = AuthorState.CHOSEN
                adapter.currentChosenAuthor?.setState(AuthorState.NOT_CHOSEN)
                adapter.currentChosenAuthor = this@SearchFavouriteAuthorsViewHolder
                setBackgroundResource(R.drawable.bg_search_authors_chosen_item)
            }
        }

        private fun setState(state: AuthorState) = with(binding.root) {
            currentState = state
            if (state == AuthorState.CHOSEN) {
                setBackgroundResource(R.drawable.bg_search_authors_chosen_item)
            }
            else {
                setBackgroundResource(R.drawable.bg_search_authors_not_chosen_item)
            }
        }
    }

    private var currentChosenAuthor: SearchFavouriteAuthorsViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFavouriteAuthorsViewHolder {
        return SearchFavouriteAuthorsViewHolder.getFrom(parent, stateListener, this)
    }

    override fun onBindViewHolder(holder: SearchFavouriteAuthorsViewHolder, position: Int) {
        val author = getItem(position)
        holder.bind(author)
    }


}