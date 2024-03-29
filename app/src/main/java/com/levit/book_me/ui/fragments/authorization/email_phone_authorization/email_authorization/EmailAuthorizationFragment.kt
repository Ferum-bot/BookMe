package com.levit.book_me.ui.fragments.authorization.email_phone_authorization.email_authorization

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentEmailAuthorizationBinding
import com.levit.bookme.uikit.utills.ParcelableClickableSpan
import com.levit.bookme.uikit.utills.ParcelableTextWatcher

class EmailAuthorizationFragment: BaseFragment(R.layout.fragment_email_authorization) {

    companion object {

        private const val EMAIL_TEXT_WATCHER_KEY = "email_text_watcher"
        private const val PASSWORD_TEXT_WATCHER_KEY = "password_text_watcher"
        private const val CLICKABLE_SPAN_KEY = "email_sign_up_clickable_span"

        private const val START_CLICKABLE_SPAN_POSITION = 28
        private const val END_CLICKABLE_SPAN_POSITION = 36

        fun newInstance(
            emailTextWatcher: ParcelableTextWatcher,
            passwordTextWatcher: ParcelableTextWatcher,
            emailSignUpClickableSpan: ParcelableClickableSpan,
        ): EmailAuthorizationFragment {
            val fragment = EmailAuthorizationFragment()
            val bundle = Bundle()
            bundle.putParcelable(EMAIL_TEXT_WATCHER_KEY, emailTextWatcher)
            bundle.putParcelable(PASSWORD_TEXT_WATCHER_KEY, passwordTextWatcher)
            bundle.putParcelable(CLICKABLE_SPAN_KEY, emailSignUpClickableSpan)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val binding by viewBinding { FragmentEmailAuthorizationBinding.bind(it) }

    private val emailTextWatcher: ParcelableTextWatcher?
    get() = arguments?.getParcelable(EMAIL_TEXT_WATCHER_KEY)

    private val passwordTextWatcher: ParcelableTextWatcher?
    get() = arguments?.getParcelable(PASSWORD_TEXT_WATCHER_KEY)

    private val clickableSpan: ParcelableClickableSpan?
    get() = arguments?.getParcelable(CLICKABLE_SPAN_KEY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTextWatchers()
        initSignUpTextView()
    }

    private fun setTextWatchers() {
        binding.emailEditText.addTextChangedListener(emailTextWatcher)
        binding.paswwordEditText.addTextChangedListener(passwordTextWatcher)
    }

    private fun initSignUpTextView() {
        configureSingUpTextView()
        val signUpText = getString(R.string.dont_have_an_account_yet)
        val spannableText = SpannableString(signUpText)

        spannableText.setSpan(
            clickableSpan,
            START_CLICKABLE_SPAN_POSITION,
            END_CLICKABLE_SPAN_POSITION,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpannableTextToTextView(spannableText)
    }

    private fun configureSingUpTextView() {
        binding.descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.descriptionTextView.highlightColor = Color.BLUE
    }

    private fun setSpannableTextToTextView(spannableText: SpannableString) {
        binding.descriptionTextView.text = spannableText
    }
}