package com.example.lf.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.lf.model.Data
import com.example.lf.model.MovieList
import com.example.lf.viewmodel.MovieListViewModel

@Composable
fun HomeScreen(onClick : (id : Int) -> Unit) {

    val movieListViewModel : MovieListViewModel = hiltViewModel()
    val state = movieListViewModel.state

    if(state.movies.isEmpty()){
        LoadingScreen()
    }
    else{
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            Modifier
                .padding(5.dp)
                .fillMaxSize()
                .background(Color.Transparent)) {
            items(state.movies.size){
                if(it>= state.movies.size -1 && !state.endPage && !state.isLoading){
                    movieListViewModel.loadMoreMovies()
                }
                ItemUI(state.movies[it], onClick)
            }
            item(state.isLoading){
                LoadingScreen()
            }
        }
    }

}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(1f),
        contentAlignment = Alignment.Center) {
        Text(text = "Loading...", style = MaterialTheme.typography.titleMedium)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemUI(data: Data, onClick: (id: Int) -> Unit) {
    Card(
        Modifier
            .padding(10.dp)
            .wrapContentSize()
            .clickable { onClick(data.id) },
        elevation = CardDefaults.cardElevation(8.dp)){

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            AsyncImage(model = data.poster,
                contentDescription = data.title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray.copy(.7f))
                    .padding(6.dp)
            ){
                Text(
                    text = data.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
