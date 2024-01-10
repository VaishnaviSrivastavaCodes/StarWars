package com.techflix.starwars.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techflix.starwars.models.StarData

@Dao
interface StarDataDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllStarData(stars: List<StarData>)

    @Query("SELECT * FROM StarData")
    suspend fun getAllStarData():List<StarData>
}