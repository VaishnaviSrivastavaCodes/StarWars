package com.techflix.starwars.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import com.techflix.starwars.R
import com.techflix.starwars.StarsApplication
import com.techflix.starwars.databinding.FragmentAllStarsGridBinding
import com.techflix.starwars.uicomponents.adapters.AllStarsGridAdapter
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory

class AllStarsGridFragment : Fragment(R.layout.fragment_all_stars_grid) {

    private lateinit var layoutBinding: FragmentAllStarsGridBinding
    private lateinit var viewmodel: StarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        layoutBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_all_stars_grid, container, false
        )
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(
            requireActivity(),
            StarViewModelFactory((requireActivity().application as StarsApplication).getRepository())
        ).get(StarViewModel::class.java)
        viewmodel.peopleLiveData.observe(requireActivity(), Observer {
            layoutBinding.allStarsGrid.apply {
                adapter = AllStarsGridAdapter(context, it.map { starData -> starData.name ?: "" })
            }
        }

        )

    }

}