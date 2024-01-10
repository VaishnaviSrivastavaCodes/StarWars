package com.techflix.StarWars.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techflix.StarWars.models.PeopleApiResponse
import com.techflix.StarWars.repository.StarsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StarViewModel(private val repository: StarsRepository) : ViewModel() {

   var peopleLiveData = MutableLiveData<PeopleApiResponse>()
    fun getData(){
        GlobalScope.launch(Dispatchers.IO){
            repository.getAllStars()?.let{
                peopleLiveData.postValue(it)
            }
        }

    }
}