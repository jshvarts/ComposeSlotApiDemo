package com.example.composeslotapidemo.data

import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: Api) {

  suspend fun getTopRatedMovies(): Result<List<Movie>> {
    return runCatching { api.getTopRated() }
  }

  suspend fun getMovies(genre: MovieGenre): Result<List<Movie>> {
    return runCatching { api.getMoviesForGenre(genre.id) }
  }
}
