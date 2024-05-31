package com.example.lf.repository

import com.example.lf.api.MovieAPI
import com.example.lf.model.Metadata
import com.example.lf.model.Movie
import com.example.lf.model.MovieList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieAPI : MovieAPI
) {

    private val _movieList = MutableStateFlow<MovieList>(MovieList(emptyList(), com.example.lf.model.Metadata("",0,0,0)))
    var movieList = _movieList.asStateFlow()

    private val _movie = MutableStateFlow<Movie>(Movie("","","","",
        emptyList(),0, emptyList(),"","","","","","","","","","","","",""))
    var movie = _movie.asStateFlow()

    suspend fun getMovieList(page: Int){
        val response = movieAPI.getMovieList(page)
        if(response.isSuccessful && response.body()!=null){
            _movieList.emit(response.body()!!)
        }
    }

    suspend fun getMovieDetails(id : Int){
        val response = movieAPI.getMovieDetails(id)
        if(response.isSuccessful && response.body()!=null){
            _movie.emit(response.body()!!)
        }
    }

}