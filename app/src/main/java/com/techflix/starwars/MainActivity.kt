package com.techflix.starwars

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.techflix.starwars.databinding.ActivityMainBinding
import com.techflix.starwars.fragments.AllStarsGridFragment
import com.techflix.starwars.fragments.BottomSheetFilterFragment
import com.techflix.starwars.fragments.BottomSheetSortFragment
import com.techflix.starwars.fragments.FilmsGridFragment
import com.techflix.starwars.utils.FragmentClickListener
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(R.layout.activity_main), FragmentClickListener {
    private lateinit var starViewModel: StarViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.sortFilterVisible = true
        binding.exitBtnText = getString(R.string.exit_app)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val repository = (application as StarsApplication).starsRepository
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<AllStarsGridFragment>(
                    R.id.fragment_container_view, AllStarsGridFragment::class.simpleName
                )
            }
        }

        starViewModel =
            ViewModelProvider(this, StarViewModelFactory(repository)).get(StarViewModel::class.java)
        starViewModel.sortFilterVisible.observe(this) {
            binding.sortFilterVisible = it
        }
        fetchData()
    }

    fun sortClicked(view: View) {
        Log.d("vvvDebug", "Sort Button Clicked")
        BottomSheetSortFragment().apply {
            show(supportFragmentManager, BottomSheetSortFragment::class.simpleName)
        }
    }

    fun filterClicked(view: View) {
        BottomSheetFilterFragment().apply {
            show(supportFragmentManager, BottomSheetFilterFragment::class.simpleName)
        }
    }

    private fun fetchData() {
        Toast.makeText(this, getText(R.string.fetching_data), Toast.LENGTH_LONG).show()
        CoroutineScope(Dispatchers.IO).launch {
            starViewModel.getAllStarsData()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentClick(position: Int) {
        starViewModel.updateSortFilterVisibility(false)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(AllStarsGridFragment::class.simpleName)
            replace<FilmsGridFragment>(R.id.fragment_container_view, args = Bundle().apply {
                putInt(FilmsGridFragment.ITEM_POSITION, position)
            })

        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        starViewModel.updateSortFilterVisibility(true)
    }
}