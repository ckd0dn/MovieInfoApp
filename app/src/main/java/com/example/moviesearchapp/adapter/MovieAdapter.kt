package com.example.moviesearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesearchapp.R
import com.example.moviesearchapp.databinding.ItemMovieBinding
import com.example.moviesearchapp.model.Search

class MovieAdapter(val onClick: (Search) -> Unit) : ListAdapter<Search, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(search: Search) {
            binding.apply {
                binding.movieName.text = search.title
                binding.movieYear.text = search.year
                binding.movieType.text = search.type

                //클릭시
                binding.root.setOnClickListener{
                    onClick(search)
                }

                Glide.with(binding.root)
                    .load(search.poster)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .into(binding.imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val search = getItem(position)
        holder.bind(search)
    }
}
