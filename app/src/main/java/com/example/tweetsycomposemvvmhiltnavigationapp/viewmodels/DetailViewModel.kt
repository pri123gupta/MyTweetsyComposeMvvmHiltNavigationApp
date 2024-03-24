package com.example.tweetsycomposemvvmhiltnavigationapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsycomposemvvmhiltnavigationapp.models.TweetListItem
import com.example.tweetsycomposemvvmhiltnavigationapp.repository.TweetsyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TweetsyRepository) : ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets
    private val _categoryname = MutableStateFlow<String>("")
    val catwgoryname: StateFlow<String>
        get() = _categoryname

    init {
        viewModelScope.launch {
            repository.getTweetsByCategory(catwgoryname.value)
        }
    }

    fun updateCategory(category: String) {
        _categoryname.value = category
    }
}