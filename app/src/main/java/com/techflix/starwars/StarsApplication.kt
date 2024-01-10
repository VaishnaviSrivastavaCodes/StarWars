package com.techflix.starwars

import android.app.Application
import com.techflix.starwars.api.RetrofitHelper
import com.techflix.starwars.api.StarService
import com.techflix.starwars.database.StarDatabase
import com.techflix.starwars.repository.StarsRepository

class StarsApplication : Application() {

    lateinit var starsRepository: StarsRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val starService = RetrofitHelper.getInstance().create(StarService::class.java)
        val starDatabase = StarDatabase.getDatabase(applicationContext)
        starsRepository = StarsRepository(starService, starDatabase)
    }

    fun getRepository(): StarsRepository {
        if (this::starsRepository.isInitialized.not()){
            onCreate()
        }
        return starsRepository
    }
}