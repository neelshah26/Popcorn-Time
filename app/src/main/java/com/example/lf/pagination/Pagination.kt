package com.example.lf.pagination

interface Pagination<Key, Item> {

    suspend fun loadNextMovies()

    fun reset()

}