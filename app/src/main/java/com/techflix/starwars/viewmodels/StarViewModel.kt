package com.techflix.starwars.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techflix.starwars.models.PeopleApiResponse
import com.techflix.starwars.models.StarData
import com.techflix.starwars.repository.StarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StarViewModel(private val repository: StarsRepository) : ViewModel() {

    var peopleLiveData = MutableLiveData<List<StarData>>()
    fun getData() {
        GlobalScope.launch(Dispatchers.IO) {
            repository.getAllStars()?.let {
                peopleLiveData.postValue(it)
            }
        }

    }
}