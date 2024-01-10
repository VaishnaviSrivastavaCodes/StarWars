package com.techflix.starwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class StarData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val birth_year: String?,
    val created: String?,
    val edited: String?,
    val eye_color: String?,
    val films: List<String>?,
    val gender: String?,
    val hair_color: String?,
    val height: String?,
    val homeworld: String?,
    val mass: String?,
    val name: String?,
    val skin_color: String?,
    val species: List<String>?,
    val starships: List<String>?,
    val url: String?,
    val vehicles: List<String>?,
)

class MyTypeConverter {
    @TypeConverter
    fun fromListOfString(list: List<String>?): String {
        return list?.let {
            it.toString().removePrefix("[").removeSuffix("]")
        } ?: ""
    }

    @TypeConverter
    fun toListOfString(s: String): List<String> {
        return s.split(",").toList()
    }
}
