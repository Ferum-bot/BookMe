package com.levit.book_me.ui.fragments.authorization.email_phone_authorization.phone_authorization

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.core.utill.PhoneRegionCodes
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentPhoneAuthorizationBinding

class PhoneAuthorizationFragment: BaseFragment(R.layout.fragment_phone_authorization) {

    private val binding by viewBinding { FragmentPhoneAuthorizationBinding.bind(it) }

    private val phoneTextWatcher: ParcelableTextWatcher?
    get() = arguments?.getParcelable(TEXT_WATCHER_KEY)

    private val spinnerAdapter by lazy {
        val codes = PhoneRegionCodes.getAll()
        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.authorization_spinner_item,
            codes
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        return@lazy adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurePhoneInput()
    }

    private fun configurePhoneInput() {
        binding.phoneEditText.addTextChangedListener(phoneTextWatcher)
        binding.countriesCodeSpinner.adapter = spinnerAdapter
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