package com.rizwan.catsapplication.di

import android.content.Context
import androidx.room.Room
import com.rizwan.catsapplication.db.CatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCatDatabase(@ApplicationContext appContext: Context): CatDatabase {
        return Room
            .databaseBuilder(appContext, CatDatabase::class.java, "catDB")
            .build()
    }

}