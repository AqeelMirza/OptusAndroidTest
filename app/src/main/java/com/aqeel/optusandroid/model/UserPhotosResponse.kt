package com.aqeel.optusandroid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserPhotosResponse(
    val id: Int?,
    val albumId: Int?,
    val thumbnailUrl: String?,
    val title: String?,
    val url: String?
) : Parcelable {
    constructor() : this(0, 0, "", "", "")
}