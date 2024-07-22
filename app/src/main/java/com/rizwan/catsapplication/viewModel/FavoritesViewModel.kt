package com.rizwan.catsapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizwan.catsapplication.models.Cat
import com.rizwan.catsapplication.models.Favorite
import com.rizwan.catsapplication.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {

    fun getFavorites(): LiveData<List<Favorite>> {
        return repository.getFavorites()
    }

    suspend fun addFavorite(cat: Cat) {
        repository.addFavorite(cat)
    }

    suspend fun removeFavorite(cat: Cat) {
        repository.removeFavorite(cat)
    }
}