package com.techflix.starwars.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techflix.starwars.models.FilmData


@Dao
interface FilmsDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllFilmsData(stars: List<FilmData>)

    @Query("SELECT * FROM FilmData")
    suspend fun getAllFilmsData(): List<FilmData>
}