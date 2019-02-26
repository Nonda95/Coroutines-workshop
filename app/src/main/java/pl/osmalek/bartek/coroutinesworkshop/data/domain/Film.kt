package pl.osmalek.bartek.coroutinesworkshop.data.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String,
    val episodeId: Int,
    val charactersIds: List<Int>
) : Parcelable