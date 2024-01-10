package com.techflix.starwars.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techflix.starwars.models.FilmData
import com.techflix.starwars.models.StarData
import com.techflix.starwars.repository.StarWarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StarViewModel(private val repository: StarWarsRepository) : ViewModel() {

    var peopleLiveData = MutableLiveData<List<StarData>>()
    val filmsLiveData = MutableLiveData<List<FilmData>>()
    fun getAllStarsData() {
        GlobalScope.launch(Dispatchers.IO) {
            repository.getAllStars()?.let {
                peopleLiveData.postValue(it)
            }
        }

    }

    fun getAllFilmsData() {
        GlobalScope.launch(Dispatchers.IO) {
            repository.getAllFilms()?.let {
                filmsLiveData.postValue(it)
            }
        }
    }
}