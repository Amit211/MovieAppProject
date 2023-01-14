package com.example.movieappproject.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieappproject.apiResponse.MovieModel
import com.example.movieappproject.reposetry.MovieListReposetry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MovieListViewModel(val context: Context): ViewModel() {

    suspend fun getMovieList(movie: String): ArrayList<MovieModel>? {
        return MovieListReposetry.callOMDBAPI(movie)
    }


}