package com.techflix.starwars.repository

import com.techflix.starwars.api.StarService
import com.techflix.starwars.database.StarDatabase
import com.techflix.starwars.models.PeopleApiResponse
import com.techflix.starwars.models.StarData
import com.techflix.starwars.utils.NetworkUtils

class StarsRepository(
    private val starService: StarService,
    private val starsDatabase: StarDatabase
) {

    suspend fun getAllStars(): List<StarData>? {
       if( NetworkUtils.isInternetAvailable()){
           val result = starService.getStarData()
           result?.apply {
               this.body()?.results?.let { starsDatabase.starsDao().saveAllStarData(it) }
           }
           return result.body()?.results
       }
        else{
            return starsDatabase.starsDao().getAllStarData()
       }

    }
}