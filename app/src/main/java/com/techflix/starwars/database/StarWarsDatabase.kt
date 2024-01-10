package com.techflix.starwars.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.techflix.starwars.models.FilmData
import com.techflix.starwars.models.MyTypeConverter
import com.techflix.starwars.models.StarData

@TypeConverters(MyTypeConverter::class)
@Database(entities = [StarData::class, FilmData::class], version = 1)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun starsDao(): StarDataDao
    abstract fun filmsDao(): FilmsDataDao

    companion object {
        @Volatile
        private var INSTANCE: StarWarsDatabase? = null

        fun getDatabase(context: Context): StarWarsDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        StarWarsDatabase::class.java,
                        "starDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}