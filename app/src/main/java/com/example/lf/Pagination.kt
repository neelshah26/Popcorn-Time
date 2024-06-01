package com.example.lf

interface Pagination<Key, Item> {

    suspend fun loadNextMovies()

    fun reset()

}