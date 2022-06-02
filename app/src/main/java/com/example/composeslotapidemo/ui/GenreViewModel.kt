package com.example.composeslotapidemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeslotapidemo.data.Movie
import com.example.composeslotapidemo.data.MovieGenre
import com.example.composeslotapidemo.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GenreUiState(
  val movies: List<Movie> = emptyList(),
  val isLoading: Boolean = false
)

@HiltViewModel
class GenreViewModel @Inject constructor(
  private val movieRepository: MovieRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(GenreUiState(isLoading = true))
  val uiState = _uiState.asStateFlow()

  fun fetchMovies(genre: MovieGenre) {
    viewModelScope.launch {
      movieRepository.getMovies(genre)
        .collect {
          _uiState.value = GenreUiState(it)
        }
    }
  }
}