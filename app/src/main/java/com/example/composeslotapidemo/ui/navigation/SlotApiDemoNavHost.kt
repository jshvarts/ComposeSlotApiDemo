package com.example.composeslotapidemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeslotapidemo.ActionMoviesScreen
import com.example.composeslotapidemo.AnimationMoviesScreen
import com.example.composeslotapidemo.HomeScreen

@Composable
fun SlotApiDemoNavHost(
  navController: NavHostController,
  modifier: Modifier
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Home.route,
    modifier = modifier
  ) {
    composable(Screen.Home.route) {
      HomeScreen(navController, modifier)
    }
    composable(Screen.ActionMovies.route) {
      ActionMoviesScreen()
    }
    composable(Screen.AnimationMovies.route) {
      AnimationMoviesScreen()
    }
  }
}