package com.example.tweetsycomposemvvmhiltnavigationapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweetsycomposemvvmhiltnavigationapp.viewmodels.DetailViewModel

//@Preview
@Composable
fun DetailScreen() {
    val vm :DetailViewModel = hiltViewModel()
    val list =  vm.tweets.collectAsState()
    LazyColumn() {
        items(list.value){
            DetailScreenItem(it.text)
        }
    }
}

//@Preview
@Composable
fun DetailScreenItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .fillMaxHeight(.1f),
        border = BorderStroke(1.dp, Color(0xFFE7608E)),
        content = {
            Text(
                text = text,
                modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}