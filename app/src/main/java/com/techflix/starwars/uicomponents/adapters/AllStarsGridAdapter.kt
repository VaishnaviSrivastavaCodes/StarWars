package com.techflix.starwars.uicomponents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.techflix.starwars.R

class AllStarsGridAdapter(private val context: Context, private val list: List<String> ) : BaseAdapter() {
    private lateinit var gridText: TextView
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
       val myItemView = LayoutInflater.from(context).inflate(R.layout.item_grid_all_stars, p2, false)
        gridText = myItemView.findViewById(R.id.star_name)
        gridText.text = list[p0]
        return myItemView
    }
}