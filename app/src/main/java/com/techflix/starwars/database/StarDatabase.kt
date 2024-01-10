package com.techflix.starwars.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.techflix.starwars.models.MyTypeConverter
import com.techflix.starwars.models.StarData

@TypeConverters(MyTypeConverter::class)
@Database(entities = [StarData::class], version = 1)
abstract class StarDatabase : RoomDatabase() {

    abstract fun starsDao(): StarDataDao

    companion object {
        @Volatile
        private var INSTANCE: StarDatabase? = null

        fun getDatabase(context: Context): StarDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        StarDatabase::class.java,
                        "starDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}