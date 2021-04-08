package com.levit.book_me.di.components

import com.levit.book_me.di.modules.authorization.AuthorizationViewModelsModule
import com.levit.book_me.core_base.di.AuthorizationScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.ui.activities.authorization.AuthorizationActivity
import com.levit.book_me.ui.fragments.authorization.choose_type_authorization.ChooseTypeAuthorizationFragment
import com.levit.book_me.ui.fragments.authorization.confirm_phone_code.ConfirmPhoneCodeFragment
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneAuthorizationContainerFragment
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.email_authorization.EmailAuthorizationFragment
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.phone_authorization.PhoneAuthorizationFragment
import com.levit.book_me.ui.fragments.authorization.email_sign_up.EmailSignUpFragment
import dagger.Subcomponent

@AuthorizationScope
@Subcomponent(modules = [
    AuthorizationViewModelsModule::class
])
interface AuthorizationComponent {

    fun inject(activity: AuthorizationActivity)

    fun inject(fragment: ChooseTypeAuthorizationFragment)

    fun inject(fragment: ConfirmPhoneCodeFragment)

    fun inject(fragment: EmailSignUpFragment)

    fun inject(fragment: EmailPhoneAuthorizationContainerFragment)

    fun inject(fragment: EmailAuthorizationFragment)

    fun inject(fragment: PhoneAuthorizationFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): AuthorizationComponent
    }
}