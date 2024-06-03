package com.example.lf

import java.io.InputStreamReader

object Helper {

    fun readJsonToString(file : String) : String{
        val input = Helper::class.java.getResourceAsStream(file)
        val builder = StringBuilder()
        val reader = InputStreamReader(input, "UTF-8")
        reader.readLines().forEach{
            builder.append(it)
        }
        return builder.toString()
    }
}