package com.levit.book_me.core_base.di

import javax.inject.Scope

@Scope
annotation class ModuleScope

@Scope
annotation class FeatureScope

@Scope
annotation class ActivityScope

@Scope
annotation class FragmentScope

@Scope
annotation class ChildFragmentScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class AuthorizationScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class OnBoardingScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class CreatingProfileScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class SearchFavouriteAuthorsScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class SearchBooksScope

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class QuotesScreenScope
