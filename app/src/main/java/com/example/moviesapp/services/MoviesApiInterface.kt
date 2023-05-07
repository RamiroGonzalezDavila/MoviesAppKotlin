package com.example.moviesapp.services

import com.example.moviesapp.models.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApiInterface {

    @GET("movie/top_rated?api_key=8e2f0ceedcf4e1a972094b863ebfdf9c")
    fun getTopList(): Call<MoviesResponse>

    @GET("movie/now_playing?api_key=8e2f0ceedcf4e1a972094b863ebfdf9c")
    fun getNowPlayingList(): Call<MoviesResponse>
}