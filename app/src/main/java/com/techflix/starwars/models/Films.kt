package com.techflix.starwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class FilmApiResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<FilmData>
)

@Entity
data class FilmData(
    @PrimaryKey
    val episode_id: Int,
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)