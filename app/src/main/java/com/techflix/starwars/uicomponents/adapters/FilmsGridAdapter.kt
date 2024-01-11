package com.techflix.starwars.uicomponents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.techflix.starwars.R
import com.techflix.starwars.databinding.ItemFilmsGridBinding
import com.techflix.starwars.databinding.ItemGridAllStarsBinding
import com.techflix.starwars.models.FilmData

class FilmsGridAdapter(
    val context: Context,
    val list: List<FilmData>,
    private val layoutInflater: LayoutInflater
) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val myItemView: ItemFilmsGridBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_films_grid, p2, false)
//            LayoutInflater.from(context).inflate( p2, false)
        myItemView.filmNameText = list[p0].title
        return myItemView.root
    }
}