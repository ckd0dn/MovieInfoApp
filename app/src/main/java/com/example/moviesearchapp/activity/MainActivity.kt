package com.example.moviesearchapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearchapp.ApiClient
import com.example.moviesearchapp.adapter.MovieAdapter
import com.example.moviesearchapp.databinding.ActivityMainBinding
import com.example.moviesearchapp.model.Movie
import com.example.moviesearchapp.network.MovieService
import retrofit2.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieService: MovieService
    private lateinit var movieAdapter: MovieAdapter
    private var page = 1
    private var haseMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieService = ApiClient.retrofit.create(MovieService::class.java)

        //어댑터
        movieAdapter = MovieAdapter {
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra("id", it.id)
            startActivity(intent)
        }

        val linearLayoutManager = LinearLayoutManager(this@MainActivity)

        //리사이클러뷰 적용
        binding.movieRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }

        //페이징 처리
        binding.movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount
                val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()

                if (lastVisiblePosition >= (totalCount - 1) && haseMore) {
                    page += 1
                    listMovies(page, 2)
                }
            }
        })

        //검색 버튼 클릭
        binding.button.setOnClickListener {
            listMovies(page, 1)
        }


    }

    private fun listMovies(page: Int, type: Int) {
        movieService.listMovies(title = binding.editText.text.toString(), page = page.toString())
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {


                    haseMore = response.body()?.search?.count() == 10
                    if (type == 1)
                        movieAdapter.submitList(response.body()?.search.orEmpty())
                    if (type == 2)
                        movieAdapter.submitList(movieAdapter.currentList + response.body()?.search.orEmpty())


                    if(response.body()?.response == "True"){
                        binding.notFoundView.isVisible = false

                    }else{
                        binding.notFoundView.isVisible = true
                        if (response.body()?.error == "Too many results.") {
                            val t =
                                Toast.makeText(this@MainActivity, "검색된 영화가 너무 많습니다", Toast.LENGTH_SHORT)
                            t.setGravity(Gravity.CENTER, 0, 0)
                            t.show()
                        } else if (response.body()?.error == "Movie not found!") {

                            val t = Toast.makeText(
                                this@MainActivity,
                                "검색된 영화를 찾지 못했습니다",
                                Toast.LENGTH_SHORT
                            )
                            t.setGravity(Gravity.CENTER, 0, 0)
                            t.show()

                        }
                    }



                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    t.printStackTrace()

                }
            })
    }

}