package com.techflix.starwars

import android.app.Application
import com.techflix.starwars.api.RetrofitHelper
import com.techflix.starwars.api.StarWarsService
import com.techflix.starwars.database.StarWarsDatabase
import com.techflix.starwars.repository.StarWarsRepository

class StarsApplication : Application() {

    lateinit var starsRepository: StarWarsRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val starService = RetrofitHelper.getInstance().create(StarWarsService::class.java)
        val starDatabase = StarWarsDatabase.getDatabase(applicationContext)
        starsRepository = StarWarsRepository(starService, starDatabase)
    }

    fun getRepository(): StarWarsRepository {
        if (this::starsRepository.isInitialized.not()){
            onCreate()
        }
        return starsRepository
    }
}