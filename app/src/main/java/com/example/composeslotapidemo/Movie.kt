package com.example.composeslotapidemo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
  val title: String
)