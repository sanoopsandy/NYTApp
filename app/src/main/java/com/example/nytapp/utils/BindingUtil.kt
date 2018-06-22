package com.example.nytapp.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.example.nytapp.R
import com.example.nytapp.adapters.BaseRecyclerAdapter
import com.squareup.picasso.Picasso


class BindingUtil {

    companion object {

        @JvmStatic
        @BindingAdapter(value = arrayOf("items"))
        fun configureAdapter(recyclerView: RecyclerView, items: List<*>) {
            try {
                val mLayoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.layoutManager = mLayoutManager
                val adapter = recyclerView.adapter as BaseRecyclerAdapter
                adapter.setItems(items)
                adapter.setContext(recyclerView.context)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {
            if (imageUrl.isNotEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.nyt_logo)
                        .into(view)
            }
        }
    }
}