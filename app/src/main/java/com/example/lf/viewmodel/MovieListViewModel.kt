package com.example.lf.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lf.pagination.PaginationFactory
import com.example.lf.model.Data
import com.example.lf.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) :ViewModel() {

    var state by mutableStateOf(ScreenState())
    private val pagination = PaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = false)
        },
        onRequest = {repository.getMovieList(it)},
        getNextKey = {state.page+1},
        onSuccess = {items, newPage ->
                    state = state.copy(
                        movies = state.movies + items.data,
                        page = newPage,
                        endPage = newPage == 25
                    )
        },
        onError ={
            state = state.copy(
                error = it?.localizedMessage
            )
        }
    )
    init {
        loadMoreMovies()
    }

    fun loadMoreMovies(){
        viewModelScope.launch {
            pagination.loadNextMovies()
        }
    }
}

data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page : Int = 1,
    val endPage: Boolean = false,
    val error: String? = "",
    val isLoading : Boolean = false
)