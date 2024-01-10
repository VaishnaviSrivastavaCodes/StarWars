package com.techflix.starwars.models

data class PeopleApiResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<StarData>
)