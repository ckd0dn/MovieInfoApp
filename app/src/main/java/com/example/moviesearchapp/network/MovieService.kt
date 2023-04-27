package com.example.moviesearchapp.network

import com.example.moviesearchapp.ApiClient
import com.example.moviesearchapp.model.Movie
import com.example.moviesearchapp.model.MovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieService {

    @GET("/?apikey=${ApiClient.MOVIE_API_KEY}")
    fun listMovies(
        @Query("s") title: String,
        @Query("page") page: String,)
    : Call<Movie>

    @GET("/?apikey=${ApiClient.MOVIE_API_KEY}")
    fun movieInfo(
        @Query("i") id: String,)
            : Call<MovieDetail>

}