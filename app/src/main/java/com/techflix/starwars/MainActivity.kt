package com.techflix.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techflix.starwars.databinding.ActivityMainBinding
import com.techflix.starwars.fragments.AllStarsGridFragment
import com.techflix.starwars.fragments.BottomSheetSortFilterFragment
import com.techflix.starwars.fragments.FilmsGridFragment
import com.techflix.starwars.uicomponents.adapters.AllStarsGridAdapter
import com.techflix.starwars.utils.FragmentClickListener
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(R.layout.activity_main), FragmentClickListener {
    private lateinit var starViewModel: StarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (application as StarsApplication).starsRepository
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<AllStarsGridFragment>(
                    R.id.fragment_container_view,
                    AllStarsGridFragment::class.simpleName
                )
            }
        }

        starViewModel =
            ViewModelProvider(this, StarViewModelFactory(repository)).get(StarViewModel::class.java)
        fetchData()
        val sortBtn = findViewById<TextView>(R.id.sort)
        sortBtn.apply {
            setOnClickListener {
                BottomSheetSortFilterFragment().apply {
                    show(supportFragmentManager, BottomSheetSortFilterFragment::class.simpleName)
                }
            }
        }

    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            starViewModel.getAllStarsData()
        }
    }

    override fun onFragmentClick(position: Int) {

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(AllStarsGridFragment::class.simpleName)
            replace<FilmsGridFragment>(R.id.fragment_container_view, args = Bundle().apply {
                putInt(FilmsGridFragment.ITEM_POSITION, position)
            })

        }
    }
}