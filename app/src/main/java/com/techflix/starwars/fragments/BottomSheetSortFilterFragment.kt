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
    lateinit var viewmodel: StarViewModel
    lateinit var layoutBinding: BottomsheetSortFilterBinding
    open var optionsList: List<String> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        layoutBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.bottomsheet_sort_filter, container, false
        )
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRadioButtons(optionsList)
        viewmodel = ViewModelProvider(
            requireActivity(),
            StarViewModelFactory((requireActivity().application as StarsApplication).getRepository())
        ).get(StarViewModel::class.java)
    }

    private fun createRadioButtons(list: List<String>) {
        layoutBinding.radioGroup.apply {
            list.forEachIndexed { index, s ->
                addView(RadioButton(requireActivity()).also { rb ->
                    rb.layoutParams = RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    rb.text = s ////setting text of second radio button
                    rb.id = index
                })
            }
            setOnCheckedChangeListener { radioGroup, i ->
                actOnRadioGroup(i)
            }
        }
    }

    open fun actOnRadioGroup(i: Int) {

    }

}

class BottomSheetSortFragment : BottomSheetSortFilterFragment() {
    override var optionsList = listOf(
        "Name(A-Z)", "Name(Z-A)", "Birth Year", "Age", "Weight", "Height", "No. of films"
    )
//        listOf(
//            getString(R.string.name) ?: "",
//            getString(R.string.name_rev),
//            getString(R.string.birth_year),
//            getString(R.string.age),
//            getString(R.string.weight),
//            getString(R.string.height),
//            getString(R.string.no_of_films)
//    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutBinding.titleText = "Sort"
    }

    override fun actOnRadioGroup(i: Int) {
        super.actOnRadioGroup(i)
        viewmodel.updateSortRadioId(i)
        dismiss()
    }
}

class BottomSheetFilterFragment : BottomSheetSortFilterFragment() {
    override var optionsList = listOf(
        "Gender-Male", "Gender-Female", "Eye Color: Blue", "Eye Color: Brown", "Reset Filter",
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutBinding.titleText = "Filter"
    }

    override fun actOnRadioGroup(i: Int) {
        super.actOnRadioGroup(i)
        viewmodel.updateFilterId(i)
        dismiss()
    }
}