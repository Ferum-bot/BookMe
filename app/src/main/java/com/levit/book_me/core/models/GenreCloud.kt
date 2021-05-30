package com.levit.book_me.core.models

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.models.RoundCloud

data class GenreCloud(

    override val text: String,

    override val size: RoundCloudSize
): RoundCloud{

    companion object {

        fun getFrom(genre: Genre): GenreCloud {
            return GenreCloud(
                text = genre.string,
                size = if (genre.isBig)
                    RoundCloudSize.LARGE
                else RoundCloudSize.SMALL
            )
        }
    }

}