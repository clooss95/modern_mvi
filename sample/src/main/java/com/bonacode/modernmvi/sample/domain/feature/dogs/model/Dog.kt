package com.bonacode.modernmvi.sample.domain.feature.dogs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dog(
    val id: Long,
    val name: String,
    val breed: String,
    val imageUrl: String
) : Parcelable
