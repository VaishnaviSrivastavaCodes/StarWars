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
    var sortId = MutableLiveData<Int>()
    var filterId = MutableLiveData<Int>()
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

    //    getString(R.string.name),
//    getString(R.string.name_rev),
//    getString(R.string.birth_year),
//    getString(R.string.age),
//    getString(R.string.weight),
//    getString(R.string.height),
//    getString(R.string.no_of_films)
    fun sortList() {
        var starList = peopleLiveData.value as MutableList
        when (sortId.value) {
            0 -> {
                starList.apply {
                    sortBy {
                        it.name
                    }
                    peopleLiveData.postValue(this)
                }
            }

            1 -> {
                starList.apply {
                    sortByDescending {
                        it.name
                    }
                    peopleLiveData.postValue(this)
                }
            }

            2 -> {
                starList.apply {
                    sortByDescending {
                        it.birth_year
                    }
                    peopleLiveData.postValue(this)
                }
            }

            3 -> {
                starList.apply {
                    sortBy {
                        it.birth_year
                    }
                    peopleLiveData.postValue(this)
                }
            }

            4 -> {
                starList.apply {
                    sortBy {
                        it.mass
                    }
                    peopleLiveData.postValue(this)
                }
            }

            5 -> {
                starList.apply {
                    sortBy {
                        it.height
                    }
                    peopleLiveData.postValue(this)
                }
            }

            6 -> {
                starList.apply {
                    sortBy {
                        it.films?.size
                    }
                    peopleLiveData.postValue(this)
                }
            }
        }
    }

    fun filterList() {
        var starList = peopleList as MutableList
        when (filterId.value) {
            0 -> {
                starList.apply {
                    filter { it.gender == "male" }.apply {
                        peopleLiveData.postValue(this)
                    }
                }

            }

            1 -> {
                starList.apply {
                    filter { it.gender == "female" }.apply { peopleLiveData.postValue(this) }

                }
            }

            2 -> {
                starList.apply {
                    filter { it.eye_color == "blue" }.apply {
                        peopleLiveData.postValue(this)
                    }

                }
            }

            3 -> {
                starList.apply {
                    filter { it.eye_color == "brown" }.apply {
                        peopleLiveData.postValue(this)
                    }

                }
            }

            4 -> {

                peopleLiveData.postValue(peopleList)
            }
        }
    }

    fun updateSortRadioId(i: Int) {
        sortId.postValue(i)
    }

    fun updateFilterId(j: Int) {
        filterId.postValue(j)
    }
}