package com.rizwan.catsapplication.models

import com.google.gson.annotations.SerializedName

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