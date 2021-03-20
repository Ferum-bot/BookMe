package com.levit.book_me.ui.fragments.authorization.email_phone_authorization.phone_authorization

import android.os.Bundle
import android.view.View
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.utill.ParcelableTextWatcher
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentPhoneAuthorizationBinding

class PhoneAuthorizationFragment: BaseFragment(R.layout.fragment_phone_authorization) {

    private val binding by viewBinding { FragmentPhoneAuthorizationBinding.bind(it) }

    private val phoneTextWatcher: ParcelableTextWatcher?
    get() = arguments?.getParcelable(TEXT_WATCHER_KEY)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPhoneWatcher()
    }

    private fun setPhoneWatcher() {

    }

    companion object {
        private const val TEXT_WATCHER_KEY = "phone_text_watcher"

        fun newInstance(phoneTextWatcher: ParcelableTextWatcher): PhoneAuthorizationFragment {
            val fragment = PhoneAuthorizationFragment()
            val bundle = Bundle()
            bundle.putParcelable(TEXT_WATCHER_KEY, phoneTextWatcher)
            fragment.arguments = bundle
            return fragment
        }
    }
}