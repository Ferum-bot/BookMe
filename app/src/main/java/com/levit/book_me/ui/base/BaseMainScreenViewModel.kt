package com.levit.book_me.ui.base

import com.levit.book_me.repositories.result_models.BaseRepositoryResult

abstract class BaseMainScreenViewModel: BaseViewModel() {

   protected open fun <T> handleErrorRepositoryResult(result: BaseRepositoryResult<T>) {
       if (result.exception?.message != null) {
           _errorMessage.postValue(result.exception?.message)
           return
       }
       if (result.errorMessage != null) {
           _errorMessage.postValue(result.errorMessage)
           return
       }
       if (result.errorMessageId != null) {
           _errorMessageId.postValue(result.errorMessageId)
           return
       }
       if (result.statusCode != null) {
           _errorMessage.postValue(result.statusCode.toString())
           return
       }
   }
}