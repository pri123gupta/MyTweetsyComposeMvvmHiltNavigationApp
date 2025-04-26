package com.example.tweetsycomposemvvmhiltnavigationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsycomposemvvmhiltnavigationapp.retrofit.TweetsyApiInterface
import com.example.tweetsycomposemvvmhiltnavigationapp.screens.CategoryScreen
import com.example.tweetsycomposemvvmhiltnavigationapp.screens.DetailScreen
import com.example.tweetsycomposemvvmhiltnavigationapp.ui.theme.TweetsyComposeMvvmHiltNavigationAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsyApiInterface: TweetsyApiInterface

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            val response = tweetsyApiInterface.getCategories()
            Log.d("PriyankaGupta", "onCreate: ${response.body()!!.distinct().toString()}")
        }
        setContent {
            TweetsyComposeMvvmHiltNavigationAppTheme(false) {
                Scaffold(
                    Modifier
                        .background(Color.White)
                        .padding(0.dp) ,
                    topBar = {
                        TopAppBar(title = {
                            Text(
                                text = "Tweets",
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .background(Color.White),
                                color = Color(0XFF050B65 ),
                                textAlign = TextAlign.Center
                            )
                        })
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "category") {

        composable(route = "category") {
            CategoryScreen() {
                navHostController.navigate("detail/${it}")
            }
        }

        composable("detail/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )) {
//            val a = it.arguments!!.getString("category")  // by default get from savedStateHandle
            DetailScreen()
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