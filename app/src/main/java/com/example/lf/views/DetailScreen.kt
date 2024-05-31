package com.example.lf.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lf.model.Movie
import com.example.lf.viewmodel.DetailViewModel


@Composable
fun DetailScreen() {

    val detailViewModel : DetailViewModel = hiltViewModel()
    val movie: State<Movie> = detailViewModel.movie.collectAsState()

    EachTeam(movie = movie.value)

}

@Composable
fun EachTeam(movie : Movie) {
    
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        border = BorderStroke(1.dp, Color(0XFEEEE))
    ){
        Column {
            Text(text = "ID: " + movie.id,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Title: " + movie.title,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Year: " + movie.year,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Actors: " + movie.actors,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Country: " + movie.country,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Rating: " + movie.imdb_rating,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(0.dp,20.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
