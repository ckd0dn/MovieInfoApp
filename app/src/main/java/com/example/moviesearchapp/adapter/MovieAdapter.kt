package com.example.moviesearchapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesearchapp.DiffCallback
import com.example.moviesearchapp.databinding.ItemMovieBinding
import com.example.moviesearchapp.model.Search
import com.example.moviesearchapp.viewholder.MovieViewHolder

class MovieAdapter() : androidx.recyclerview.widget.ListAdapter<Search, MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            parent.context,
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }


//    companion object {
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Search>() {
//            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }

    //    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(search: Search) {
//            binding.apply {
//                binding.movieName.text = search.title
//                binding.movieYear.text = search.year
//                binding.movieType.text = search.type
//
//                //클릭시
//                binding.root.setOnClickListener{
//                    onClick(search)
//                }
//
//                Glide.with(binding.root)
//                    .load(search.poster)
//                    .error(R.drawable.ic_baseline_error_outline_24)
//                    .into(binding.imageView)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val binding =
//            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MovieViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val search = getItem(position)
//        holder.bind(search)
//    }



}
