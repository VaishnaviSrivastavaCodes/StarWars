package com.techflix.starwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techflix.starwars.api.RetrofitHelper
import com.techflix.starwars.api.StarService
import com.techflix.starwars.fragments.AllStarsGridFragment
import com.techflix.starwars.repository.StarsRepository
import com.techflix.starwars.viewmodels.StarViewModel
import com.techflix.starwars.viewmodels.StarViewModelFactory


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var starViewModel: StarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<AllStarsGridFragment>(R.id.fragment_container_view)
            }
        }


        val starService = RetrofitHelper.getInstance().create(StarService::class.java)
        val repository = StarsRepository(starService)
        starViewModel =
            ViewModelProvider(this, StarViewModelFactory(repository)).get(StarViewModel::class.java)
        starViewModel.peopleLiveData.observe(this, Observer {
            Log.d("Vaishnavi", it.results.toString())
        })
        starViewModel.getData()

    }
}