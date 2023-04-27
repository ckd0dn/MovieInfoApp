package com.example.moviesearchapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val MOVIE_API_KEY = "47b5b2e9"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
}