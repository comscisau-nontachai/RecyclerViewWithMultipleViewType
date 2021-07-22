package com.example.recyclerviewwithmultipleviewtype.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithmultipleviewtype.R
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemDirectorBinding
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemMovieBinding
import com.example.recyclerviewwithmultipleviewtype.databinding.ItemTitleBinding
import kotlinx.coroutines.withTimeoutOrNull
import java.lang.IllegalArgumentException

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    var items = listOf<HomeRecyclerViewItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var itemClickListener : ((view : View, item : HomeRecyclerViewItem, position : Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return when(viewType){
            R.layout.item_director ->{
                HomeRecyclerViewHolder.DirectorVH(ItemDirectorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_movie ->{
                HomeRecyclerViewHolder.MovieVH(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            R.layout.item_title ->{
                HomeRecyclerViewHolder.TitleVH(ItemTitleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when(holder){
            is HomeRecyclerViewHolder.DirectorVH -> holder.bind(items[position] as HomeRecyclerViewItem.Director)
            is HomeRecyclerViewHolder.MovieVH -> holder.bind(items[position] as HomeRecyclerViewItem.Movie)
            is HomeRecyclerViewHolder.TitleVH -> holder.bind(items[position] as HomeRecyclerViewItem.Title)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is HomeRecyclerViewItem.Director -> R.layout.item_director
            is HomeRecyclerViewItem.Movie -> R.layout.item_movie
            is HomeRecyclerViewItem.Title -> R.layout.item_title
        }
    }
}
