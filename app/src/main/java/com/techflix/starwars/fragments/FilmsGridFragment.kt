package com.techflix.starwars.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.techflix.starwars.R
import com.techflix.starwars.StarsApplication
import com.techflix.starwars.databinding.FragmentAllStarsGridBinding
import com.techflix.starwars.databinding.FragmentFilmDetailsBinding
import com.techflix.starwars.uicomponents.adapters.FilmsGridAdapter

import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmsGridFragment : Fragment() {
    companion object {
        const val ITEM_POSITION = "itemPosition"
    }

    private lateinit var layoutBinding: FragmentFilmDetailsBinding
    private lateinit var viewmodel: StarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        layoutBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_film_details, container, false
        )
        viewmodel = ViewModelProvider(
            requireActivity(),
            StarViewModelFactory((requireActivity().application as StarsApplication).getRepository())
        ).get(StarViewModel::class.java)
        arguments?.getInt(ITEM_POSITION)?.let {
            Toast.makeText(requireActivity(), getText(R.string.fetching_data), Toast.LENGTH_LONG)
                .show()
            CoroutineScope(Dispatchers.IO).launch {
                viewmodel.getAllFilmsData(viewmodel.peopleLiveData.value?.get(it)?.films)
            }

        }

        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.filmsLiveData.observe(requireActivity(), Observer {
            layoutBinding.moviesGrid.apply {
                adapter = FilmsGridAdapter(context, it, layoutInflater)
            }
        })


    }
}