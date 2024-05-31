package com.example.lf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lf.model.MovieList
import com.example.lf.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) :ViewModel() {

    val movieList: StateFlow<MovieList>
        get() = repository.movieList

    init {
        viewModelScope.launch {
            repository.getMovieList(1)
        }
    }
}