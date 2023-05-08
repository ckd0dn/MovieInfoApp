package com.example.moviesearchapp.viewholder

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesearchapp.R
import com.example.moviesearchapp.activity.MainActivity
import com.example.moviesearchapp.activity.MovieActivity
import com.example.moviesearchapp.databinding.ItemMovieBinding
import com.example.moviesearchapp.model.Search

class MovieViewHolder(
    private val context: Context,
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Search) {
        binding.item = item

        binding.root.setOnClickListener {

                Intent(context, MovieActivity::class.java).apply {
                putExtra("id", item.id)
            }.run { context.startActivity(this) }

        }

//        Glide.with(binding.root)
//            .load(item.poster)
//            .error(R.drawable.ic_baseline_error_outline_24)
//            .into(binding.imageView)
    }


}