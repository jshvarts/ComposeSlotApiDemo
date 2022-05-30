package com.example.composeslotapidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeslotapidemo.ui.theme.ComposeSlotApiDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeSlotApiDemoTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          HomeScreen()
        }
      }
    }
  }
}

@Composable
fun HomeScreen(movieListViewModel: MovieListViewModel = viewModel()) {
  val movies by movieListViewModel.moviesStateFlow.collectAsState()
  LazyColumn {
    items(movies) { movie ->
      Text(movie.title)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
  ComposeSlotApiDemoTheme {
  }
}