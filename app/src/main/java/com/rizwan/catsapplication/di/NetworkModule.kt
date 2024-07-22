package com.rizwan.catsapplication.di

import com.rizwan.catsapplication.BuildConfig
import com.rizwan.catsapplication.api.CatAPI
import com.rizwan.catsapplication.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()

            .addInterceptor(interceptor)

            .build()
    }

    @Singleton
    @Provides
    fun provideCatAPI(retrofit: Retrofit) : CatAPI {
        return retrofit.create(CatAPI::class.java)
    }
}