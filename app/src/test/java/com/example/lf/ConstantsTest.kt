package com.example.lf

import org.junit.Assert.*

import org.junit.Test

class ConstantsTest {
    @Test
    fun isBaseURL_Correct(){
        val result = Constants.BASE_URL

        assertEquals("https://moviesapi.ir/api/v1/", result)
    }

    @Test
    fun isPalindrome_expected_false() {
        val result = Constants.isPalindrome("hello")

        assertEquals(false, result)
    }

    @Test
    fun isPalindrome_expected_true() {
        val result = Constants.isPalindrome("level")

        assertEquals(true, result)
    }
}