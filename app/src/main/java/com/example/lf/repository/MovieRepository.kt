package com.example.lf.repository

import com.example.lf.api.MovieAPI
import com.example.lf.model.Movie
import com.example.lf.model.MovieList
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieAPI : MovieAPI
) {

    suspend fun getMovieDetails(id : Int) : Response<Movie>{
        return movieAPI.getMovieDetails(id)
    }

    suspend fun getMovieList(page: Int): Response<MovieList>{
        return movieAPI.getMovieList(page)
    }

}