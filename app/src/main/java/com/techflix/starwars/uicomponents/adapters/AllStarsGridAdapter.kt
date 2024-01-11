package com.techflix.starwars.uicomponents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.techflix.starwars.R
import com.techflix.starwars.databinding.ItemGridAllStarsBinding
import com.techflix.starwars.models.StarData
import com.techflix.starwars.utils.ItemClickListener


class AllStarsGridAdapter(
    private val context: Context,
    private val list: List<StarData>,
    private val layoutInflater: LayoutInflater,
    private val listener: ItemClickListener,
) :
    BaseAdapter() {
    private var mItemClickListener: ItemClickListener? = null
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {
        val itemViewBinding: ItemGridAllStarsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_grid_all_stars, p2, false)
        itemViewBinding.apply {
            list[i].let {
                starNameText = it.name
                starBYText = context.getString(R.string.birth_year_, it.birth_year ?: "")
                starGenderText = context.getString(R.string.gender_, it.gender ?: "")
                starHeightText = context.getString(R.string.height_, it.height ?: "")
                starHairColorText = context.getString(R.string.hair_color_, it.hair_color ?: "")
                starEyeColorText = context.getString(R.string.eye_color_, it.eye_color ?: "")

            }
        }
        itemViewBinding.root.setOnClickListener {
            listener?.onItemClick(i)
        }
        return itemViewBinding.root
    }

}