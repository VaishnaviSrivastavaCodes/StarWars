package com.techflix.StarWars.repository

import com.techflix.StarWars.api.StarService
import com.techflix.StarWars.models.PeopleApiResponse

class StarsRepository(private val starService: StarService) {
    suspend fun getAllStars(): PeopleApiResponse? {
        val result = starService.getStarData()
        return result.body()
    }
}