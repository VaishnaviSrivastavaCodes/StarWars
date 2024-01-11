package com.techflix.starwars.repository

import android.util.Log
import com.techflix.starwars.api.StarWarsService
import com.techflix.starwars.database.StarWarsDatabase
import com.techflix.starwars.models.FilmData
import com.techflix.starwars.models.StarData
import com.techflix.starwars.utils.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class StarWarsRepository(
    private val starWarsService: StarWarsService,
    private val starsDatabase: StarWarsDatabase
) {

    suspend fun getAllStars(): List<StarData> {
        if (NetworkUtils.isInternetAvailable()) {
            val result = starWarsService.getStarData()
            Log.d("VAPI0", result.body().toString())
            result?.apply {
                Log.d("VAPIResponse", this.body().toString())
                this.body()?.results?.let { starsDatabase.starsDao().saveAllStarData(it) }
            }
            return result.body()?.results ?: listOf()
        } else {
            return starsDatabase.starsDao().getAllStarData()
        }

//        val result = starWarsService.getStarData()
//        return result.body()?.results ?: listOf()

    }

    suspend fun getFilmData(urls: List<String>): List<FilmData>? {
        var resList = mutableListOf<FilmData>()
        var resItem: FilmData? = null
        if (NetworkUtils.isInternetAvailable()) {
            urls.forEach {
                CoroutineScope(Dispatchers.IO).async {
                    val result = starWarsService.getFilmsData(it)
                    result?.apply {
                        this.body()?.let {
                            resItem = it
                        }
                    }
                }.let {
                    it.await()
                    resItem?.let {
                        resList.add(it)
//                        starsDatabase.filmsDao().saveFilmData(it)
                    }

                }
            }
            return resList
        } else {
            return listOf()
        }
    }
}