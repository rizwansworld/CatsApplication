package com.rizwan.catsapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rizwan.catsapplication.models.Favorite

@Dao
interface CatDao {
    @Insert
    suspend fun addFavorite(favorite: Favorite)

    @Delete
    suspend fun removeFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    fun getFavorites() : LiveData<List<Favorite>>
}