package com.example.composeslotapidemo

import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: Api) {

  suspend fun getMovies(): Result<List<Movie>> {
    return kotlin.runCatching {
      api.getTopRated()
    }
  }
}