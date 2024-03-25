package com.example.tweetsycomposemvvmhiltnavigationapp.repository

import com.example.tweetsycomposemvvmhiltnavigationapp.models.TweetListItem
import com.example.tweetsycomposemvvmhiltnavigationapp.retrofit.TweetsyAPI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsyRepository @Inject constructor(private val api: TweetsyAPI) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getTweetsByCategory(category: String) {
        val result = api.getTweetsByCategory("tweets[?(@.category==\"$category\")]")
        if (result.isSuccessful && result.body() != null) {
            _tweets.value = result.body()!!
        }
    }

    suspend fun getCategories() {
        val result = api.getCategories()
        if (result.isSuccessful && result.body() != null) {
            _categories.value = result.body()!!
        }
    }
}