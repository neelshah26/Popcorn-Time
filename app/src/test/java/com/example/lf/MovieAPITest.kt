package com.example.lf

import com.example.lf.api.MovieAPI
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieAPITest {

    lateinit var mockserver: MockWebServer
    lateinit var movieAPI: MovieAPI
    @Before
    fun setup(){
        mockserver = MockWebServer()
        movieAPI = Retrofit.Builder()
            .baseUrl(mockserver.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }

    @Test
    fun testGetMovieListSuccess(){
        runTest {
            val mockResponse = MockResponse()
            val body = Helper.readJsonToString("/testdata_movies_list.json")
            mockResponse.setBody(body)
            mockResponse.setResponseCode(200)
            mockserver.enqueue(mockResponse)

            val response = movieAPI.getMovieList(1)
            mockserver.takeRequest()

            Assert.assertEquals(2, response.body()!!.data.size)
        }
    }

    @Test
    fun testGetMovieListFailure(){
        runTest {
            val mockResponse = MockResponse()
            mockResponse.setBody("")
            mockResponse.setResponseCode(404)
            mockserver.enqueue(mockResponse)

            val response = movieAPI.getMovieList(1)
            mockserver.takeRequest()

            Assert.assertEquals(false, response.isSuccessful)
        }
    }

    @Test
    fun testGetMovieDetails(){
        runTest {
            val mockResponse = MockResponse()
            val body = Helper.readJsonToString("/testdata_movie_details.json")
            mockResponse.setBody(body)
            mockResponse.setResponseCode(200)
            mockserver.enqueue(mockResponse)

            val response = movieAPI.getMovieDetails(1)
            mockserver.takeRequest()

            Assert.assertEquals("The Shawshank Redemption", response.body()!!.title)
        }
    }

    @After
    fun tearDown(){
        mockserver.shutdown()
    }
}