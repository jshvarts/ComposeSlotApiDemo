package com.example.composeslotapidemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeslotapidemo.data.Movie
import com.example.composeslotapidemo.data.MovieGenre
import com.example.composeslotapidemo.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {

  private val _topRatedMovies = MutableStateFlow(emptyList<Movie>())
  val topRatedMovies = _topRatedMovies.asStateFlow()

  private val _actionMovies = MutableStateFlow(emptyList<Movie>())
  val actionMovies = _actionMovies.asStateFlow()

  private val _animationMovies = MutableStateFlow(emptyList<Movie>())
  val animationMovies = _animationMovies.asStateFlow()

  private val _error = MutableSharedFlow<Unit>()
  val error = _error.asSharedFlow()

  init {
    viewModelScope.launch {
      runCatching {
        movieRepository.getTopRatedMovies()
      }.onSuccess { result ->
        _topRatedMovies.emit(result.getOrDefault(emptyList()))
      }.onFailure {
        _error.emit(Unit)
      }

      runCatching {
        movieRepository.getMovies(MovieGenre.ACTION)
      }.onSuccess { result ->
        _actionMovies.emit(result.getOrDefault(emptyList()))
      }.onFailure {
        _error.emit(Unit)
      }

      runCatching {
        movieRepository.getMovies(MovieGenre.ANIMATION)
      }.onSuccess { result ->
        _animationMovies.emit(result.getOrDefault(emptyList()))
      }.onFailure {
        _error.emit(Unit)
      }
    }
  }
}