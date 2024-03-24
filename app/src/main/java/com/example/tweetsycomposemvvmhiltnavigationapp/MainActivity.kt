package com.example.tweetsycomposemvvmhiltnavigationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tweetsycomposemvvmhiltnavigationapp.retrofit.TweetsyAPI
import com.example.tweetsycomposemvvmhiltnavigationapp.screens.CategoryItem
import com.example.tweetsycomposemvvmhiltnavigationapp.screens.CategoryScreen
import com.example.tweetsycomposemvvmhiltnavigationapp.screens.DetailScreen
import com.example.tweetsycomposemvvmhiltnavigationapp.screens.DetailScreenItem
import com.example.tweetsycomposemvvmhiltnavigationapp.ui.theme.TweetsyComposeMvvmHiltNavigationAppTheme
import com.example.tweetsycomposemvvmhiltnavigationapp.viewmodels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsyAPI: TweetsyAPI
    //    @Inject
//    lateinit var catvm= CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val response = tweetsyAPI.getCategories()
            Log.d("PriyankaGupta", "onCreate: ${response.body()!!.distinct().toString()}")
        }

        setContent {

            TweetsyComposeMvvmHiltNavigationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    DetailScreen()
//                    DetailScreenItem("eryiuw eiujid siajx jaqouw wijkax xswk hiwhdk ")
                    CategoryScreen{
//                        DetailScreen(it)
                    }
//                    CategoryItem()
//                    Greeting("Android ")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TweetsyComposeMvvmHiltNavigationAppTheme {
        Greeting("Android")
    }
}