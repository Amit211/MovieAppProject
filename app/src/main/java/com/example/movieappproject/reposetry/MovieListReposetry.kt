package com.example.movieappproject.reposetry

import android.annotation.SuppressLint
import android.util.Log
import com.example.movieappproject.apiResponse.MovieModel
import com.indev.claraa.restApi.MovieAPI
import com.indev.claraa.restApi.ClientApi

class MovieListReposetry {

    companion object {
        val apiInterface = ClientApi.getClient()?.create(MovieAPI::class.java)

        lateinit var movieArrayList: ArrayList<MovieModel>

        @SuppressLint("SuspiciousIndentation")
        suspend fun callOMDBAPI(movie: String): ArrayList<MovieModel>?  {
            movieArrayList= ArrayList<MovieModel>()
            try {
                val result = apiInterface?.callAPI(movie)
                if (result?.body()!!.Response.equals("True")){
                    movieArrayList.addAll(result?.body()!!.Search)
                    Log.e("TAG", "Lists: " + movieArrayList)
                 }

            } catch (e: Exception) {
                Log.d("fail", "$e")
            }
            return movieArrayList
        }
    }
}