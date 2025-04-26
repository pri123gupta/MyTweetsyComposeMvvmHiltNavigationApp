package com.example.tweetsycomposemvvmhiltnavigationapp.retrofit

import com.example.tweetsycomposemvvmhiltnavigationapp.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApiInterface {

    @GET("/v3/b/65fdb6a51f5677401f415f9a?meta=false")             //self
    suspend fun getAllTweets(): Response<List<TweetListItem>>

    @GET("/v3/b/65fdb6a51f5677401f415f9a?meta=false")               // dynamic string as header
    suspend fun getTweetsByCategory(@Header("X-JSON-Path") category :String ): Response<List<TweetListItem>>

    @GET("/v3/b/65fdb6a51f5677401f415f9a?meta=false")
    @Headers("X-JSON-Path: tweets..category")                       // static string as header
    suspend fun getCategories(): Response<List<String>>


}