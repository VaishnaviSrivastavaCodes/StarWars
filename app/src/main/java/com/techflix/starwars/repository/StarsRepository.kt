package com.techflix.starwars.repository

import com.techflix.starwars.api.StarService
import com.techflix.starwars.models.PeopleApiResponse

class StarsRepository(private val starService: StarService) {
    suspend fun getAllStars(): PeopleApiResponse? {
        val result = starService.getStarData()
        return result.body()
    }
}