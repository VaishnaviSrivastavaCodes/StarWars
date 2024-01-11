package com.techflix.starwars.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techflix.starwars.models.FilmData
import com.techflix.starwars.models.StarData
import com.techflix.starwars.repository.StarWarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StarViewModel(private val repository: StarWarsRepository) : ViewModel() {

    var peopleLiveData = MutableLiveData<List<StarData>>()
    val filmsLiveData = MutableLiveData<List<FilmData>>()
    var peopleList = listOf<StarData>()
    var filmsList = listOf<FilmData>()
    suspend fun getAllStarsData() {
        viewModelScope.async(Dispatchers.IO) {
            repository.getAllStars()?.let {
                peopleList = it
            }
        }.let {
            it.await()
            peopleLiveData.postValue(peopleList)
        }

    }

    suspend fun getAllFilmsData(listUrls: List<String>?) {
        viewModelScope.async(Dispatchers.IO) {
            if (listUrls != null) {
                repository.getFilmData(listUrls)?.let {
                    filmsList = it
                }
            }
        }.let {
            it.await()
            filmsLiveData.postValue(filmsList)
        }
    }
}