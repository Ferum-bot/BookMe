package com.levit.book_me.ui.fragments.authorization.email_phone_authorization.phone_authorization

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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

    private var countryCode: String = "+7"

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
        configureSpinner()
    }

    private fun configurePhoneInput() {
        binding.phoneEditText.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable) {
                phoneTextWatcher?.afterTextChanged(s)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                phoneTextWatcher?.beforeTextChanged(s, start, count, after)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val rowNumber = s.toString()
                val telephoneNumber = "$countryCode $rowNumber"
                phoneTextWatcher?.onTextChanged(telephoneNumber, start, before, count)
            }

        })
    }

    private fun configureSpinner() {
        binding.countriesCodeSpinner.adapter = spinnerAdapter
        binding.countriesCodeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val countryCodes = PhoneRegionCodes.getAllCodes()
                countryCode = countryCodes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
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