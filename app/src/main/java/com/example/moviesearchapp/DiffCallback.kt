package com.example.moviesearchapp

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearchapp.model.Search

class DiffCallback<T : Search> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

}