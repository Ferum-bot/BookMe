package com.levit.book_me.roundcloudsview.core.models

import android.os.Parcelable
import android.view.View
import kotlinx.android.parcel.Parcelize

/**
 * This data class needed to safe RoundCloudsView state.
 * View.BaseSavedState is parcelable of View.java class.
 */
@Parcelize
internal data class CloudsViewStateModel(

    val cloudModels: List<RoundCloudModel> = emptyList(),

    val baseStateModel: View.BaseSavedState? = null

): Parcelable
