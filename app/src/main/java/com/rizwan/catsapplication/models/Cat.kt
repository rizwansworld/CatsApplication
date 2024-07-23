package com.rizwan.catsapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val description: String = "",
    val id: String = "",
    val lifeSpan: String = "",
    val name: String = "",
    val origin: String = "",
    val imageUrl: String = ""
) : Parcelable