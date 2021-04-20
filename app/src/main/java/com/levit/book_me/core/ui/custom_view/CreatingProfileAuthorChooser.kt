package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.auth.api.Auth
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.CreatingProfileAuthorChooseLayoutBinding

class CreatingProfileAuthorChooser @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    interface AuthorChangeListener {

        fun onAuthorAdd(authorPosition: AuthorPosition)

        fun onAuthorRemoved(authorPosition: AuthorPosition, author: Author)
    }

    enum class AuthorPosition(val pos: Int) {
        FIRST_POSITION(0), SECOND_POSITION(1), THIRD_POSITION(2),
        FOURS_POSITION(3), FIVES_POSITION(4);

        companion object {

            fun getFrom(pos: Int): AuthorPosition = when(pos) {
                0 -> FIRST_POSITION
                1 -> SECOND_POSITION
                2 -> THIRD_POSITION
                3 -> FOURS_POSITION
                4 -> FIVES_POSITION
                else ->
                    throw IllegalArgumentException("Undefined pos to get from: $pos")
            }
        }
    }

    private enum class AuthorStatuses {
        CHOSEN, EMPTY
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

    fun setAuthor(position: AuthorPosition, author: Author) {
        val pos = position.pos
        authorsTextViewList[pos].text = author.fullName
        authorsChangeButtonList[pos].setImageResource(R.drawable.ic_minus)
        authorStatuses[pos] = AuthorStatuses.CHOSEN
        authorsList[pos] = author
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
            val pos = AuthorPosition.getFrom(position)
            when(authorStatuses[position]) {
                AuthorStatuses.EMPTY -> onEmptyAuthorClicked(pos)
                AuthorStatuses.CHOSEN -> onChosenAuthorClicked(pos)
            }
        }
    }

    private fun onEmptyAuthorClicked(position: AuthorPosition) {
        authorChangeListener?.onAuthorAdd(position) ?: return
    }

    private fun onChosenAuthorClicked(position: AuthorPosition) {
        val pos = position.pos
        val author = authorsList[pos] ?: return
        authorChangeListener?.onAuthorRemoved(position, author) ?: return
        authorStatuses[pos] = AuthorStatuses.EMPTY
        authorsChangeButtonList[pos].setImageResource(R.drawable.ic_plus)
        authorsList[pos] = null

        with(authorsTextViewList[pos]) {
            text = when(position) {
                AuthorPosition.FIRST_POSITION -> getString(R.string.first_author)
                AuthorPosition.SECOND_POSITION -> getString(R.string.second_author)
                AuthorPosition.THIRD_POSITION -> getString(R.string.third_author)
                AuthorPosition.FOURS_POSITION -> getString(R.string.fours_author)
                AuthorPosition.FIVES_POSITION -> getString(R.string.fives_author)
            }
        }
    }

    private fun getString(@StringRes id: Int): String =
        context.getString(id)
}