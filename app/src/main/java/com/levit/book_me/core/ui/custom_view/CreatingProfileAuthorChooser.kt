package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.CreatingProfileAuthorChooseLayoutBinding

class CreatingProfileAuthorChooser @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    /**
     * Author positions is number between 0 to 4
     */
    interface AuthorChangeListener {

        fun onAuthorAdd(authorPosition: Int)

        fun onAuthorRemoved(authorPosition: Int, author: Author)
    }

    private enum class AuthorStatuses {
        CHOSEN, EMPTY
    }

    companion object {
        private const val FIRST_POSITION = 0
        private const val SECOND_POSITION = 1
        private const val THIRD_POSITION = 2
        private const val FOURS_POSITION = 3
        private const val FIVES_POSITION = 4
    }

    private val binding: CreatingProfileAuthorChooseLayoutBinding

    private var authorChangeListener: AuthorChangeListener? = null

    private val authorsList: MutableList<Author?> = mutableListOf(
        null, null, null, null, null
    )

    private val authorStatuses: MutableList<AuthorStatuses> = mutableListOf(
        AuthorStatuses.EMPTY,  AuthorStatuses.EMPTY,  AuthorStatuses.EMPTY,
        AuthorStatuses.EMPTY,  AuthorStatuses.EMPTY,
    )

    private lateinit var authorsTextViewList: List<TextView>
    private lateinit var authorsChangeButtonList: List<ImageView>

    init {
        val inflater = LayoutInflater.from(context)
        binding = CreatingProfileAuthorChooseLayoutBinding
            .inflate(inflater, this, true)

        configureLayout()
    }

    fun setAuthorChangeListener(listener: AuthorChangeListener?) {
        authorChangeListener = listener
        setAuthorChangeClickListeners()
    }

    fun setAuthor(position: Int, author: Author) {
        if (isInvalidPosition(position)) {
            return
        }

        authorsTextViewList[position].text = author.fullName
        authorsChangeButtonList[position].setImageResource(R.drawable.ic_minus)
        authorStatuses[position] = AuthorStatuses.CHOSEN
        authorsList[position] = author
    }

    fun getAllAuthors(): List<Author?> = authorsList

    private fun configureLayout() {
        authorsTextViewList = listOf(
            binding.firstAuthor, binding.secondAuthor, binding.thirdAuthor,
            binding.foursAuthor, binding.fivesAuthor
        )

        authorsChangeButtonList = listOf(
            binding.firstAddButton, binding.secondAddButton, binding.thirdAddButton,
            binding.foursAddButton, binding.fivesAddButton
        )

        setAuthorChangeClickListeners()
    }

    private fun setAuthorChangeClickListeners() =
      authorsChangeButtonList.forEachIndexed { position, authorChangeView ->
        authorChangeView.setOnClickListener {
            when(authorStatuses[position]) {
                AuthorStatuses.EMPTY -> onEmptyAuthorClicked(position)
                AuthorStatuses.CHOSEN -> onChosenAuthorClicked(position)
            }
        }
    }

    private fun onEmptyAuthorClicked(position: Int) {
        authorChangeListener?.onAuthorAdd(position) ?: return
    }

    private fun onChosenAuthorClicked(position: Int) {
        val author = authorsList[position] ?: return
        authorChangeListener?.onAuthorRemoved(position, author) ?: return
        authorStatuses[position] = AuthorStatuses.EMPTY
        authorsChangeButtonList[position].setImageResource(R.drawable.ic_plus)
        authorsList[position] = null

        with(authorsTextViewList[position]) {
            text = when(position) {
                FIRST_POSITION -> getString(R.string.first_author)
                SECOND_POSITION -> getString(R.string.second_author)
                THIRD_POSITION -> getString(R.string.third_author)
                FOURS_POSITION -> getString(R.string.fours_author)
                FIVES_POSITION -> getString(R.string.fives_author)
                else -> getString(R.string.author)
            }
        }
    }

    private fun getString(@StringRes id: Int): String =
        context.getString(id)

    private fun isInvalidPosition(position: Int): Boolean =
        position !in 0..4
}