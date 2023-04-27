package com.example.moviesearchapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Search")
    val search: List<Search>,

    @SerializedName("Response")
    val response: String,

    @SerializedName("Error")
    val error: String,
)

data class Search(
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("imdbID")
    val id: String,

    @SerializedName("Type")
    val type: String,

    @SerializedName("Poster")
    val poster: String,

)