package com.example.lf.pagination

import retrofit2.Response

class PaginationFactory<Key, Item>(
    private val initialPage: Key,
    private inline val onLoadUpdated : (Boolean) -> Unit,
    private inline val onRequest : suspend (nextKey: Key) -> Response<Item>,
    private inline val getNextKey : suspend (Item) -> Key,
    private inline val onSuccess : suspend (items: Item, newPage :Key) ->Unit,
    private inline val onError : suspend (Throwable?) -> Unit,

): Pagination<Key, Item> {

    private var currentPage = initialPage
    private var isMakingReq = false

    override suspend fun loadNextMovies() {
        if(isMakingReq)
            return
        isMakingReq = true
        onLoadUpdated(true)
        try {
            val response = onRequest(currentPage)
            if(response.isSuccessful) {
                isMakingReq = false
                val items = response.body()!!
                currentPage = getNextKey(items)
                onSuccess(items, currentPage)
                onLoadUpdated(false)
            }
        }catch (e: Exception){
            onError(e)
            onLoadUpdated(false)
        }
    }

    override fun reset() {
        currentPage = initialPage
    }
}