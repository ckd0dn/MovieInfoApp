package com.example.moviesearchapp.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("Title")
    val title: String,

    @SerializedName("Poster")
    val poster: String,

    @SerializedName("Released")
    val released: String,

    @SerializedName("Runtime")
    val runtime: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Actors")
    val actors: String,

    @SerializedName("Plot")
    val plot: String,

    @SerializedName("Metascore")
    val metascore: String,

    @SerializedName("imdbRating")
    val rating: String,

)