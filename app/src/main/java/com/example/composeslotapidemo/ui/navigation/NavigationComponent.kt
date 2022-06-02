package com.example.composeslotapidemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeslotapidemo.ActionMoviesScreen
import com.example.composeslotapidemo.AnimationMoviesScreen
import com.example.composeslotapidemo.HomeScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
  NavHost(navController, startDestination = Screen.Home.route) {
    composable(Screen.Home.route) {
      HomeScreen(navController)
    }
    composable(Screen.ActionMovies.route) {
      ActionMoviesScreen()
    }
    composable(Screen.AnimationMovies.route) {
      AnimationMoviesScreen()
    }
  }
}