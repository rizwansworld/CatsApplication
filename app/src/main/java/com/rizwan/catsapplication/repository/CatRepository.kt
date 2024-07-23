package com.rizwan.catsapplication.repository

import androidx.lifecycle.LiveData
import com.rizwan.catsapplication.api.CatAPI
import com.rizwan.catsapplication.db.CatDatabase
import com.rizwan.catsapplication.models.Cat
import com.rizwan.catsapplication.models.CatsState
import com.rizwan.catsapplication.models.Favorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catAPI: CatAPI,
    private val catMapper: ApiCatMapper,
    private val catDatabase: CatDatabase
) {

    private val _cats = MutableStateFlow<CatsState>(CatsState())
    val cats: StateFlow<CatsState>
        get() = _cats

    suspend fun getCatsList() {
        _cats.emit(
            CatsState(isLoading = true)
        )

        val result = catAPI.getCats()

        if (result.isSuccessful && result.body() != null) {
            try {
                val cats = result.body()!!.map { catMapper.mapToDomain(it) }
                _cats.emit(
                    CatsState(cats = cats)
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _cats.emit(
                    CatsState(isError = true)
                )
            }
        } else {
            _cats.emit(
                CatsState(isError = true)
            )
        }
    }

    suspend fun addFavorite(cat: Cat) {
        catDatabase.catDao().addFavorite(Favorite(cat.id))
    }

    suspend fun removeFavorite(cat: Cat) {
        catDatabase.catDao().removeFavorite(Favorite(cat.id))
    }

    fun getFavorites(): LiveData<List<Favorite>> {
        return catDatabase.catDao().getFavorites()
    }

}