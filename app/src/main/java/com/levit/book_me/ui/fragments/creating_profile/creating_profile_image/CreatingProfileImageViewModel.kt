package com.levit.book_me.ui.fragments.creating_profile.creating_profile_image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.di.CreatingProfileScope
import javax.inject.Inject

@CreatingProfileScope
class CreatingProfileImageViewModel @Inject constructor(): ViewModel() {

    private val _isPhotoChosen: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPhotoChosen: LiveData<Boolean> = _isPhotoChosen

}