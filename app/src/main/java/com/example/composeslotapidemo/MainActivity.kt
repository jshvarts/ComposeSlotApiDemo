package com.example.composeslotapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composeslotapidemo.ui.theme.ComposeSlotApiDemoTheme
import com.skydoves.landscapist.coil.CoilImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

private const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val homeViewModel by viewModels<HomeViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeSlotApiDemoTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          HomeScreen(homeViewModel)
        }
      }
    }
  }
}

@Composable
fun HomeScreen(
  homeViewModel: HomeViewModel,
  modifier: Modifier = Modifier
) {
  Column(modifier.padding(vertical = 16.dp)) {
    Text(
      text = stringResource(id = R.string.screen_title_home),
      style = MaterialTheme.typography.h3,
      modifier = Modifier.padding(horizontal = 16.dp)
    )

    HomeSection(title = R.string.section_title_top_rated) {
      TopRatedMovieList(homeViewModel = homeViewModel)
    }

    HomeSection(title = R.string.section_title_action) {
      ActionMovieList(homeViewModel = homeViewModel)
    }

    HomeSection(title = R.string.section_title_animation) {
      AnimationMovieList(homeViewModel = homeViewModel)
    }
  }
}

@Composable
fun HomeSection(
  @StringRes title: Int,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  Column(modifier) {
    Text(
      text = stringResource(id = title).uppercase(Locale.getDefault()),
      style = MaterialTheme.typography.h5,
      modifier = Modifier
        .paddingFromBaseline(top = 42.dp, bottom = 16.dp)
        .padding(horizontal = 16.dp)
    )
    content()
  }
}

@Composable
fun TopRatedMovieList(homeViewModel: HomeViewModel) {
  val movies by homeViewModel.topRatedMovies.collectAsState()

  LazyRow(
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(
      start = 16.dp,
      end = 16.dp
    )
  ) {
    items(movies) { movie ->
      CoilImage(
        imageModel = POSTER_IMAGE_BASE_URL + movie.posterPath,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .clip(shape = RoundedCornerShape(16.dp))
      )
    }
  }
}

@Composable
fun ActionMovieList(homeViewModel: HomeViewModel) {
  val movies by homeViewModel.actionMovies.collectAsState()

  LazyRow {
    items(movies) { movie ->
      Text(movie.title)
    }
  }
}

@Composable
fun AnimationMovieList(homeViewModel: HomeViewModel) {
  val movies by homeViewModel.animationMovies.collectAsState()

  LazyRow {
    items(movies) { movie ->
      Text(movie.title)
    }
  }
}

@Composable
fun ActionMoviesScreen(homeViewModel: HomeViewModel) {
  Text(
    text = stringResource(id = R.string.screen_title_action_movies),
    style = MaterialTheme.typography.h3,
    modifier = Modifier.padding(horizontal = 16.dp)
  )
}

@Composable
fun AnimationMoviesScreen(homeViewModel: HomeViewModel) {
  Text(
    text = stringResource(id = R.string.screen_title_animation_movies),
    style = MaterialTheme.typography.h3,
    modifier = Modifier.padding(horizontal = 16.dp)
  )
}

