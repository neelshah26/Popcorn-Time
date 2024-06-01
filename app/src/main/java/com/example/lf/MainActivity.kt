package com.example.lf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lf.api.MovieAPI
import com.example.lf.repository.MovieRepository
import com.example.lf.ui.theme.LFTheme
import com.example.lf.viewmodel.MovieListViewModel
import com.example.lf.views.DetailScreen
import com.example.lf.views.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: MovieRepository

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LFTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "Netflix", color = Color.Black) })
                    }
                ) {
                    Box(modifier = Modifier.padding(it)){
                        App()
                    }
                }
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

