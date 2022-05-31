package com.example.composeslotapidemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeslotapidemo.ActionMoviesScreen
import com.example.composeslotapidemo.AnimationMoviesScreen
import com.example.composeslotapidemo.HomeScreen
import com.example.composeslotapidemo.HomeViewModel

@Composable
fun NavigationComponent(
  navController: NavHostController,
  homeViewModel: HomeViewModel
) {
  NavHost(navController, startDestination = Screen.Home.route) {
    composable(Screen.Home.route) {
      HomeScreen(homeViewModel, navController)
    }
    composable(Screen.ActionMovies.route) {
      ActionMoviesScreen(homeViewModel)
    }
    composable(Screen.AnimationMovies.route) {
      AnimationMoviesScreen(homeViewModel)
    }
  }
}