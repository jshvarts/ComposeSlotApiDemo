package com.example.composeslotapidemo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
  val title: String,
  @Json(name = "poster_path") val posterPath: String
)