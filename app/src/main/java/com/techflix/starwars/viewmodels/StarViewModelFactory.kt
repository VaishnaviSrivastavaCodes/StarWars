package com.techflix.starwars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techflix.starwars.repository.StarWarsRepository

class StarViewModelFactory(private val repository: StarWarsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StarViewModel(repository) as T
    }
}