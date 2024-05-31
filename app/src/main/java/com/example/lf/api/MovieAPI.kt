package com.example.lf.api

import com.example.lf.model.Movie
import com.example.lf.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movies?/")
    suspend fun getMovieList(
        @Query("page") page: Int
    ) : Response<MovieList>

    @GET("movies/{id}")
    suspend fun getMovieDetails(
        @Path("id") movie_id : Int
    ) : Response<Movie>
}