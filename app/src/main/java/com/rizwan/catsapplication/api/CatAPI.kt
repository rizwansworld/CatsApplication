package com.rizwan.catsapplication.api

import com.rizwan.catsapplication.models.ApiCat
import retrofit2.Response
import retrofit2.http.GET

interface CatAPI {
    @GET("v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&page=0&limit=20")
    suspend fun getCats(): Response<List<ApiCat>>
}