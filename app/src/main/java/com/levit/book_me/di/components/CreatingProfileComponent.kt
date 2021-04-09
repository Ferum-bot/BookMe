package com.levit.book_me.di.components

import com.levit.book_me.di.modules.creating_profile.CreatingProfileViewModelsModule
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.di.modules.creating_profile.CreatingProfileInteractorsModule
import com.levit.book_me.di.modules.firebase.FirebaseDataSourceModule
import com.levit.book_me.di.modules.firebase.FirebaseRepositoryModule
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.fragments.creating_profile.creating_name_surname.CreatingNameSurnameFragment
import dagger.Subcomponent

@CreatingProfileScope
@Subcomponent(modules = [
    CreatingProfileViewModelsModule::class,
    CreatingProfileInteractorsModule::class,
    FirebaseDataSourceModule::class,
    FirebaseRepositoryModule::class,
])
interface CreatingProfileComponent {

    fun inject(activity: CreatingProfileActivity)

    fun inject(fragment: CreatingNameSurnameFragment)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {

        fun build(): CreatingProfileComponent
    }
}