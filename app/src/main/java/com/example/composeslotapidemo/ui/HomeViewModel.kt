package com.example.composeslotapidemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeslotapidemo.WhileUiSubscribed
import com.example.composeslotapidemo.data.Movie
import com.example.composeslotapidemo.data.MovieGenre
import com.example.composeslotapidemo.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
  val topRatedMovies: List<Movie> = emptyList(),
  val actionMovies: List<Movie> = emptyList(),
  val animationMovies: List<Movie> = emptyList(),
  val isLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
  movieRepository: MovieRepository
) : ViewModel() {

  val uiState: StateFlow<HomeUiState> = combine(
    movieRepository.getTopRatedMovies(),
    movieRepository.getMovies(MovieGenre.ACTION),
    movieRepository.getMovies(MovieGenre.ANIMATION)
  ) { topRated, action, animation ->
    HomeUiState(
      topRated,
      action,
      animation
    )
  }
    .stateIn(
      scope = viewModelScope,
      started = WhileUiSubscribed,
      initialValue = HomeUiState(isLoading = true)
    )
}