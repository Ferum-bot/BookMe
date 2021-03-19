package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.phone_authorization.PhoneAuthorizationFragment

class EmailPhoneViewPagerAdapter(
    fragment: EmailPhoneAuthorizationContainerFragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        PAGE_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            FIRST_POSITION -> PhoneAuthorizationFragment()
            SECOND_POSITION -> PhoneAuthorizationFragment()
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