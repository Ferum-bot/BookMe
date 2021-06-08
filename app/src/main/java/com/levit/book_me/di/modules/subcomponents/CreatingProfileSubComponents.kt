package com.levit.book_me.di.modules.subcomponents

import com.levit.book_me.di.components.QuotesComponent
import com.levit.book_me.di.components.SearchBooksComponent
import com.levit.book_me.di.components.SearchFavouriteAuthorsComponent
import dagger.Module

@Module(subcomponents = [
    SearchFavouriteAuthorsComponent::class,
    SearchBooksComponent::class,
    QuotesComponent::class,
])
open class CreatingProfileSubComponents