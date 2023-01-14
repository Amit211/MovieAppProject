package com.indev.claraa.restApi


import com.example.movieappproject.apiResponse.SearchListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieAPI {

    @GET("?apikey=b9bd48a6&type=movie")
    suspend fun callAPI(@Query("s") movie: String): Response<SearchListModel>

}