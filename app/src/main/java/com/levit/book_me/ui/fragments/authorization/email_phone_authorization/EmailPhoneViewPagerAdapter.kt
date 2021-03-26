package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.levit.book_me.core.ui.ParcelableClickableSpan
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.email_authorization.EmailAuthorizationFragment
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.phone_authorization.PhoneAuthorizationFragment

class EmailPhoneViewPagerAdapter(
    fragment: EmailPhoneAuthorizationContainerFragment,
    private val emailTextWatcher: ParcelableTextWatcher,
    private val phoneTextWatcher: ParcelableTextWatcher,
    private val emailSignUpClickableSpan: ParcelableClickableSpan
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        PAGE_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            FIRST_POSITION -> PhoneAuthorizationFragment.newInstance(phoneTextWatcher)
            SECOND_POSITION -> EmailAuthorizationFragment.newInstance(emailTextWatcher, emailSignUpClickableSpan)
            else ->
                throw IllegalArgumentException("Unexpected position to create fragment: $position")
        }
    }

    companion object {
        private const val PAGE_COUNT = 2

        const val FIRST_POSITION = 0
        const val SECOND_POSITION = 1
    }
}