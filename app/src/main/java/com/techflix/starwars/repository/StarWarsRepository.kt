package com.techflix.starwars.repository

import com.techflix.starwars.api.StarWarsService
import com.techflix.starwars.database.StarWarsDatabase
import com.techflix.starwars.models.FilmData
import com.techflix.starwars.models.StarData
import com.techflix.starwars.utils.NetworkUtils

class StarWarsRepository(
    private val starWarsService: StarWarsService,
    private val starsDatabase: StarWarsDatabase
) {

    suspend fun getAllStars(): List<StarData>? {
        if (NetworkUtils.isInternetAvailable()) {
            val result = starWarsService.getStarData()
            result?.apply {
                this.body()?.results?.let { starsDatabase.starsDao().saveAllStarData(it) }
            }
            return result.body()?.results
        } else {
            return starsDatabase.starsDao().getAllStarData()
        }

    }

    suspend fun getAllFilms(): List<FilmData>? {
        if (NetworkUtils.isInternetAvailable()) {
            val result = starWarsService.getFilmsData()
            result?.apply {
                this.body()?.results?.let { starsDatabase.filmsDao().saveAllFilmsData(it) }
            }
            return result.body()?.results
        } else {
            return starsDatabase.filmsDao().getAllFilmsData()
        }
    }
}