package com.techflix.starwars.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.techflix.starwars.R
import com.techflix.starwars.databinding.FragmentAllStarsGridBinding
import com.techflix.starwars.uicomponents.adapters.AllStarsGridAdapter

class AllStarsGridFragment : Fragment(R.layout.fragment_all_stars_grid) {

    private lateinit var layoutBinding: FragmentAllStarsGridBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_all_stars_grid,
            container,
            false
        )
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutBinding.allStarsGrid.apply {
            adapter = AllStarsGridAdapter(context, listOf("a", "b", "c", "d", "e"))
        }
    }

}