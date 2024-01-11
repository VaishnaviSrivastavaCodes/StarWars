package com.techflix.starwars.utils

import com.techflix.starwars.models.StarData

interface ItemClickListener {
    fun onItemClick(position: Int)
}

interface FragmentClickListener {
    fun onFragmentClick(position: Int)
}