package com.techflix.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techflix.starwars.fragments.AllStarsGridFragment
import com.techflix.starwars.fragments.BottomSheetSortFilterFragment
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var starViewModel: StarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (application as StarsApplication).starsRepository
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<AllStarsGridFragment>(R.id.fragment_container_view)
            }
        }

        starViewModel =
            ViewModelProvider(this, StarViewModelFactory(repository)).get(StarViewModel::class.java)
        starViewModel.getAllStarsData()
        starViewModel.getAllFilmsData()
        val sortBtn = findViewById<TextView>(R.id.sort)
        sortBtn.apply {
            setOnClickListener {
                BottomSheetSortFilterFragment().apply {
                    show(supportFragmentManager, "abc")
                }
            }
        }

    }
}