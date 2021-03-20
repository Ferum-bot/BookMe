package com.levit.book_me.ui.fragments.authorization.email_phone_authorization.email_authorization

import android.os.Bundle
import android.view.View
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.utill.ParcelableTextWatcher
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentEmailAuthorizationBinding

class EmailAuthorizationFragment: BaseFragment(R.layout.fragment_email_authorization) {

    private val binding by viewBinding { FragmentEmailAuthorizationBinding.bind(it) }

    private val emailTextWatcher: ParcelableTextWatcher?
    get() = arguments?.getParcelable(TEXT_WATCHER_KEY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEmailWatcher()
    }

    private fun setEmailWatcher() {
        binding.emailEditText.addTextChangedListener(emailTextWatcher)
    }

    companion object {

        private const val TEXT_WATCHER_KEY = "email_text_watcher"

        fun newInstance(emailTextWatcher: ParcelableTextWatcher): EmailAuthorizationFragment {
            val fragment = EmailAuthorizationFragment()
            val bundle = Bundle()
            bundle.putParcelable(TEXT_WATCHER_KEY, emailTextWatcher)
            fragment.arguments = bundle
            return fragment
        }
    }
}