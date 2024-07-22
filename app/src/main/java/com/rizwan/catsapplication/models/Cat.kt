package com.rizwan.catsapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ApiCat(
    @SerializedName("breeds")
    val breeds: List<Breed>,
    @SerializedName("url")
    val url: String
)

data class Breed(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: String
)

@Parcelize
data class Cat(
    val description: String = "",
    val id: String = "",
    val lifeSpan: String = "",
    val name: String = "",
    val origin: String = "",
    val imageUrl: String = ""
) : Parcelable