package com.techflix.StarWars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techflix.StarWars.repository.StarsRepository

class StarViewModelFactory(private val repository: StarsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StarViewModel(repository) as T
    }
}