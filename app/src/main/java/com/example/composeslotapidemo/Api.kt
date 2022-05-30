package com.example.composeslotapidemo

import retrofit2.http.GET

interface Api {
  @GET("movie/top_rated")
  @WrapperMovieResults
  suspend fun getTopRated(): List<Movie>
}