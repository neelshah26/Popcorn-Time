package com.example.lf.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.lf.R
import com.example.lf.model.Movie
import com.example.lf.viewmodel.DetailViewModel


@Composable
fun DetailScreen() {

    val detailViewModel : DetailViewModel = hiltViewModel()
    val movie: State<Movie> = detailViewModel.movie.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        MoviePoster(movie.value)
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(text = movie.value.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center)

            MovieAttributes(movie.value, modifier = Modifier)
            TextBlocks(icon = Icons.Filled.Info, heading = "Movie Plot", text= movie.value.plot)
            TextBlocks(icon = Icons.Filled.Person, heading = "Movie Actors", text= movie.value.actors)
        }
    }


}

@Composable
fun TextBlocks(icon: ImageVector, heading: String, text: String) {
    Row{
        Icon(imageVector = icon, contentDescription = "", tint = Color.White)
        Text(
            text = heading,
            Modifier.padding(start = 10.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
    Text(text = text, color = Color.White)

}

@Composable
fun MovieAttributes(value: Movie, modifier: Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Icon(imageVector = Icons.Filled.Star, contentDescription = "", tint = Color.White)
        Text(
            text = value.rated,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "",
            tint = Color.White
        )
        Text(
            text = value.runtime,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
        Spacer(modifier = modifier.width(25.dp))
        Icon(imageVector = Icons.Filled.DateRange, contentDescription = "", tint = Color.White)
        Text(
            text = value.released,
            modifier.padding(start = 6.dp),
            color = Color.White
        )
    }
}

@Composable
fun MoviePoster(value: Movie) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(250.dp)
            .padding(top = 80.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        AsyncImage(
            model = value.poster, contentDescription = value.title,
            Modifier
                .width(250.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .width(250.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )
    }
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
//EachTeam(movie = movie.value)
