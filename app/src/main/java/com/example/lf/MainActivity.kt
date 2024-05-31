package com.example.lf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lf.api.MovieAPI
import com.example.lf.ui.theme.LFTheme
import com.example.lf.views.DetailScreen
import com.example.lf.views.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LFTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                App()
            }
        }
    }
}

@Composable
fun App() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movie_list_screen") {

        composable(route = "movie_list_screen"){
            HomeScreen{
                navController.navigate("detail/${it}")
            }
        }

        composable(route = "detail/{movie_id}",
            arguments = listOf(
                navArgument("movie_id"){
                    type = NavType.IntType
                }
            )
            ){
                DetailScreen()
        }

    }

}

