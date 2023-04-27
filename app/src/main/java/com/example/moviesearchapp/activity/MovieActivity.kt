package com.example.moviesearchapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesearchapp.ApiClient
import com.example.moviesearchapp.R
import com.example.moviesearchapp.databinding.ActivityMainBinding
import com.example.moviesearchapp.databinding.ActivityMovieInfoBinding
import com.example.moviesearchapp.model.Movie
import com.example.moviesearchapp.model.MovieDetail
import com.example.moviesearchapp.network.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMovieInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieID = intent.getStringExtra("id")

        val movieService = ApiClient.retrofit.create(MovieService::class.java)

        if (movieID != null) {
            movieService.movieInfo( id = movieID ).enqueue(object: Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    binding.title.text = response.body()?.title ?: ""
                    binding.released.text = "공개일자: ${response.body()?.released}"
                    binding.runtime.text = "방영시간: ${response.body()?.runtime}"
                    binding.genre.text = "장르: ${response.body()?.genre}"
                    binding.director.text = "제작: ${response.body()?.director}"
                    binding.actors.text = "출연: ${response.body()?.actors}"
                    binding.plot.text = response.body()?.plot ?: ""
                    binding.metascore.text = "metaScore: ${response.body()?.metascore}"
                    binding.rating.text = response.body()?.rating ?: ""

                    Glide.with(binding.root)
                        .load(response.body()?.poster)
                        .error(R.drawable.ic_baseline_error_outline_24)
                        .into(binding.imageView)

                    binding.imageView.setOnClickListener {
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${response.body()?.title}"))
                        startActivity(intent)
                    }

                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }



    }
}