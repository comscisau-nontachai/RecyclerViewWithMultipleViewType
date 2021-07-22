package com.example.recyclerviewwithmultipleviewtype.ui

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recyclerviewwithmultipleviewtype.R
import com.example.recyclerviewwithmultipleviewtype.databinding.ActivityMainBinding
import com.example.recyclerviewwithmultipleviewtype.ui.adapter.HomeRecyclerViewAdapter
import com.example.recyclerviewwithmultipleviewtype.ui.adapter.HomeRecyclerViewItem
import com.example.recyclerviewwithmultipleviewtype.ui.adapter.TestAdapter
import java.util.stream.LongStream

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val testAdapter by lazy { TestAdapter() }
    private val movieAdapter by lazy { HomeRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**for test adapter*/
        /*val list = listOf<HomeRecyclerViewItem.Title>(HomeRecyclerViewItem.Title(1, "new movice 001"),HomeRecyclerViewItem.Title(2, "new movice 002"))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = testAdapter
        testAdapter.listener = { view, item, position ->
            Toast.makeText(this, "item => ${item.title} pos => ${position}", Toast.LENGTH_SHORT).show()
        }
        testAdapter.items = list*/
        /**end*/



        val movieList = mutableListOf<HomeRecyclerViewItem>()
        movieList.add(HomeRecyclerViewItem.Title(1,"Recommended Movies"))
        movieList.addAll(getMovie())
        movieList.add(HomeRecyclerViewItem.Title(2,"Top Directors"))
        movieList.addAll(getDirector())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        movieAdapter.items = movieList
        movieAdapter.itemClickListener = { view, item, position ->
            val msg = when(item){
                is HomeRecyclerViewItem.Director -> "Director click ${item.name}"
                is HomeRecyclerViewItem.Movie -> "Movie click ${item.title}"
                is HomeRecyclerViewItem.Title -> "View All ${item.title}"
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getMovie() : List<HomeRecyclerViewItem.Movie> {
        return listOf(
            HomeRecyclerViewItem.Movie(1,"Captain America","https://i.ibb.co/3ffM9SK/captain-america.jpg","----"),
            HomeRecyclerViewItem.Movie(2,"Avengers Endgame","https://i.ibb.co/xCndmYK/endgame.jpg","----")
        )
    }

    private fun getDirector() : List<HomeRecyclerViewItem.Director>{
        return listOf(
            HomeRecyclerViewItem.Director(1,"Russo Brothers","https://i.ibb.co/TbR4V3b/russo.jpg",14),
            HomeRecyclerViewItem.Director(2,"Steven Spielberg","https://i.ibb.co/y5rDhKp/steven.jpg",2),
            HomeRecyclerViewItem.Director(3,"Christopher Nolan","https://i.ibb.co/Zd3gs7Y/christopher.jpg",5),
            HomeRecyclerViewItem.Director(4,"Russo Brothers","https://i.ibb.co/y5rDhKp/russo.jpg",8),
            HomeRecyclerViewItem.Director(5,"Christopher Nolan","https://i.ibb.co/Zd3gs7Y/christopher.jpg",6)
        )
    }

}

fun ImageView.loadImage(url: String) {
    try {
        Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_background).into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}