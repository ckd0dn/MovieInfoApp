package com.example.moviesearchapp

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url : String?) {
        Glide.with(imageView.context)
            .load(url)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(imageView)
    }

}

