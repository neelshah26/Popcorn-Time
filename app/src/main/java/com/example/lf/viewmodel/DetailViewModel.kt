package com.example.lf.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lf.model.Movie
import com.example.lf.model.MovieList
import com.example.lf.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) :ViewModel() {

    var state by mutableStateOf(MovieState())

    init {
        viewModelScope.launch {
            val movie_id = savedStateHandle.get<Int>("movie_id") ?: 1
            val response = repository.getMovieDetails(movie_id)
            state = state.copy(
                movie = response.body()!!
            )
        }
    }
}

data class MovieState(
    val movie: Movie = Movie("","","","", emptyList(),0, emptyList(),"","","","","","","","","","","","","")
)