package com.example.tweetsycomposemvvmhiltnavigationapp.di

import com.example.tweetsycomposemvvmhiltnavigationapp.retrofit.TweetsyApiInterface
import com.example.tweetsycomposemvvmhiltnavigationapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    //retrofit, api

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesAPIinterface(retrofit: Retrofit): TweetsyApiInterface {
        return retrofit.create(TweetsyApiInterface::class.java)
    }
}