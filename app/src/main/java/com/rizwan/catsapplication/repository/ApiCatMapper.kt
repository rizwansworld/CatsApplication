package com.rizwan.catsapplication.repository

import com.rizwan.catsapplication.models.ApiCat
import com.rizwan.catsapplication.models.Cat
import com.rizwan.catsapplication.utils.ApiMapper
import javax.inject.Inject

class ApiCatMapper @Inject constructor(): ApiMapper<ApiCat, Cat> {
    override fun mapToDomain(input: ApiCat): Cat {
        return Cat(
            id = input.breeds[0].id,
            name = input.breeds[0].name,
            description = input.breeds[0].description,
            lifeSpan = input.breeds[0].lifeSpan,
            origin = input.breeds[0].origin,
            imageUrl = input.url
        )
    }
}