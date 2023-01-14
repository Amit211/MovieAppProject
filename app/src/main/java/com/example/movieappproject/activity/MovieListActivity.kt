package com.example.movieappproject.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieappproject.R
import com.example.movieappproject.adapter.MovieListAdapter
import com.example.movieappproject.apiResponse.MovieModel
import com.example.movieappproject.databinding.ActivityMovieListBinding
import com.example.movieappproject.factory.MovieListFactory
import com.example.movieappproject.viewModel.MovieListViewModel
import kotlinx.coroutines.*


class MovieListActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieListBinding
    private lateinit var movieListViewModel: MovieListViewModel
    lateinit var movieListAdapter: MovieListAdapter
    lateinit var movieList: ArrayList<MovieModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)
        title= "Search Movie"

        movieListViewModel = ViewModelProvider(
            this,
            MovieListFactory(this)
        )[MovieListViewModel::class.java]
        binding.moviewVM = movieListViewModel


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchViewItem = menu.findItem(R.id.search_bar)
        val searchView = MenuItemCompat.getActionView(searchViewItem) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Override onQueryTextSubmit method which is call when submit query is searched
            override fun onQueryTextSubmit(query: String): Boolean {
                getSearchMovieList(query)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                getSearchMovieList(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getSearchMovieList(query: String) {
        if(query.isNotEmpty()){
            binding.rvMovieList.visibility = View.VISIBLE
            binding.animationView.visibility = View.GONE
        }else{
            binding.rvMovieList.visibility = View.GONE
            binding.animationView.visibility = View.VISIBLE
        }
        CoroutineScope(Dispatchers.IO).launch {
            movieList = movieListViewModel.getMovieList(query) as ArrayList<MovieModel>
            withContext(Dispatchers.Main) {
                movieListAdapter = MovieListAdapter(applicationContext, movieList)
                recycleViewMovieList()
            }
        }
    }

    private fun recycleViewMovieList() {
        binding.rvMovieList.apply {
            setHasFixedSize(true)
            val layoutManager = GridLayoutManager(applicationContext, 2)
            binding.rvMovieList.layoutManager =layoutManager
            adapter = movieListAdapter
        }
    }
}