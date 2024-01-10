package com.techflix.starwars.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techflix.starwars.R
import com.techflix.starwars.StarsApplication
import com.techflix.starwars.databinding.BottomsheetSortFilterBinding
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory

open class BottomSheetSortFilterFragment : BottomSheetDialogFragment() {
    private lateinit var viewmodel: StarViewModel
    private lateinit var layoutBinding: BottomsheetSortFilterBinding
    private var optionsList: List<String> = listOf("Name", "Game", "Tame")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bottomsheet_sort_filter,
            container,
            false
        )
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutBinding.radioGroup.apply {
            optionsList.forEachIndexed { index, s ->
                addView(RadioButton(requireActivity()).also { rb ->
                    rb.layoutParams = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    rb.setText(s) ////setting text of second radio button
                    rb.id = index
                })
            }
        }
        viewmodel = ViewModelProvider(
            requireActivity(),
            StarViewModelFactory((requireActivity().application as StarsApplication).getRepository())
        ).get(StarViewModel::class.java)
    }

}