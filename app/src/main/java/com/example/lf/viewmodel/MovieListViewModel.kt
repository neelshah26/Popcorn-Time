package com.example.lf.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.lf.PaginationFactory
import com.example.lf.model.Data
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

//    init {
//        viewModelScope.launch {
//            val response = repository.getMovieList(state.page)
//            state = state.copy(
//                movies = response.body()!!.data,
//                page = response.body()!!.metadata.current_page.toInt()
//            )
//        }
//    }

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