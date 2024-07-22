package com.rizwan.catsapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rizwan.catsapplication.models.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class CatDatabase : RoomDatabase() {

    abstract fun catDao() : CatDao

}