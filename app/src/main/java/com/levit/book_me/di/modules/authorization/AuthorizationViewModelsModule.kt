package com.levit.book_me.di.modules.authorization

import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.annotations.ViewModelKey
import com.levit.book_me.ui.fragments.authorization.choose_type_authorization.ChooseTypeAuthorizationViewModel
import com.levit.book_me.ui.fragments.authorization.confirm_phone_code.ConfirmPhoneCodeViewModel
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneAuthorizationViewModel
import com.levit.book_me.ui.fragments.authorization.email_sign_up.EmailSignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthorizationViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ChooseTypeAuthorizationViewModel::class)
    abstract fun bindChooseTypeAuthorizationViewModel(viewModel: ChooseTypeAuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmailPhoneAuthorizationViewModel::class)
    abstract fun bindEmailPhoneAuthorizationViewModel(viewModel: EmailPhoneAuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmPhoneCodeViewModel::class)
    abstract fun bindConfirmPhoneCodeViewModel(viewModel: ConfirmPhoneCodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmailSignUpViewModel::class)
    abstract fun bindEmailSignUpViewModel(viewModel: EmailSignUpViewModel): ViewModel

}