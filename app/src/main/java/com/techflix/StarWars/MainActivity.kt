package com.techflix.StarWars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.techflix.StarWars.models.PeopleApiResponse
import com.techflix.StarWars.network.ApiInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}