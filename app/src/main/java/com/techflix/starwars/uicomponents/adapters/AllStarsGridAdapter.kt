package com.techflix.starwars.uicomponents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.techflix.starwars.R
import com.techflix.starwars.databinding.ItemGridAllStarsBinding
import com.techflix.starwars.utils.ItemClickListener


class AllStarsGridAdapter(
    private val context: Context,
    private val list: List<String>,
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

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val myItemView: ItemGridAllStarsBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_grid_all_stars, p2, false)
//            LayoutInflater.from(context).inflate( p2, false)
        myItemView.starNameText = list[p0]
        myItemView.starName.setOnClickListener {
            listener?.onItemClick(p0)
        }
        return myItemView.root
    }

}