package com.example.lf

import com.example.lf.model.Metadata
import com.example.lf.model.Movie
import com.example.lf.model.MovieList

object Constants {
    const val BASE_URL = "https://moviesapi.ir/api/v1/"

    val empty_Movie: Movie = Movie(
        actors = "",
        awards = "",
        country = "",
        director = "",
        genres = emptyList(),
        id = 0,
        images = emptyList(),
        imdb_id = "",
        imdb_rating = "",
        imdb_votes = "",
        metascore = "",
        plot = "",
        poster = "",
        rated = "",
        released = "",
        runtime = "",
        title = "",
        type = "",
        writer = "",
        year = ""
    )

    // isPalindrome() used only for showing unit testing
    fun isPalindrome(s :String) : Boolean{
        val ans = true
        var i=0
        var j=s.length -1
        while (i<j){
            if(s[i]!=s[j]){
                return false
            }
            i++
            j--
        }
        return ans
    }
}