package com.example.composeslotapidemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {

  private val _moviesStateFlow = MutableStateFlow(emptyList<Movie>())
  val moviesStateFlow = _moviesStateFlow.asStateFlow()

  private val _errorResId = MutableSharedFlow<Int>()
  val errorResId = _errorResId.asSharedFlow()

  init {
    viewModelScope.launch {
      kotlin.runCatching {
        movieRepository.getMovies()
      }.onSuccess { result ->
        _moviesStateFlow.emit(result.getOrDefault(emptyList()))
      }.onFailure {
        _errorResId.emit(R.string.app_name)
      }
    }
  }
}