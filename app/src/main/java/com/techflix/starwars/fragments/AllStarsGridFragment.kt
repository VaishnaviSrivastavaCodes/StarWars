package com.techflix.starwars.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techflix.starwars.R
import com.techflix.starwars.StarsApplication
import com.techflix.starwars.databinding.FragmentAllStarsGridBinding
import com.techflix.starwars.models.StarData
import com.techflix.starwars.uicomponents.adapters.AllStarsGridAdapter
import com.techflix.starwars.utils.FragmentClickListener
import com.techflix.starwars.utils.ItemClickListener
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory
import java.lang.ClassCastException


class AllStarsGridFragment : Fragment(R.layout.fragment_all_stars_grid), ItemClickListener {

    private lateinit var layoutBinding: FragmentAllStarsGridBinding
    private lateinit var viewmodel: StarViewModel
    private lateinit var mFragmentClickListener: FragmentClickListener

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
        viewmodel.apply {
            peopleLiveData.observe(
                requireActivity()
            ) {
                layoutBinding.allStarsGrid.apply {
                    adapter = AllStarsGridAdapter(
                        context,
                        it.map { starData -> starData.name ?: "" },
                        layoutInflater,
                        listener = this@AllStarsGridFragment
                    )
                }
            }
            sortId.observe(requireActivity()) {
                sortList()
            }
            filterId.observe(requireActivity()){
                filterList()
            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val a: Activity
        if (context is FragmentClickListener) {
            try {
                mFragmentClickListener = context
            } catch (e: ClassCastException) {
                Log.d("Error", "Listener not implemented")
            }
        }
    }

    override fun onItemClick(position: Int) {
        mFragmentClickListener.onFragmentClick(position)
    }

}