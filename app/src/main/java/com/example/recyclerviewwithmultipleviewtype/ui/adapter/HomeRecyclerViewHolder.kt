package com.example.recyclerviewwithmultipleviewtype.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.recyclerviewwithmultipleviewtype.R
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemDirectorBinding
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemMovieBinding
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemTitleBinding
import com.example.recyclerviewwithmultipleviewtype.ui.loadImage

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener : ((view : View , item : HomeRecyclerViewItem , position : Int) -> Unit)? = null

    class TitleVH(private val binding: ItemTitleBinding) : HomeRecyclerViewHolder(binding){
        fun bind(item : HomeRecyclerViewItem.Title){
            binding.textViewTitle.text = item.title
            binding.textViewAll.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }

    class MovieVH(private val binding: ItemMovieBinding) : HomeRecyclerViewHolder(binding){
        fun bind(item : HomeRecyclerViewItem.Movie){
            binding.imageViewMovie.loadImage(item.thumbnail)
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }

    class DirectorVH(private val binding: ItemDirectorBinding) : HomeRecyclerViewHolder(binding) {
        fun bind(item : HomeRecyclerViewItem.Director){
            binding.imageViewDirector.loadImage(item.avatar)
            binding.textViewName.text = item.name
            binding.textViewMovies.text = "${item.movie_count} movies"
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }
}