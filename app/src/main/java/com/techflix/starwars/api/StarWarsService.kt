package com.techflix.starwars.api

import com.techflix.starwars.models.FilmApiResponse
import com.techflix.starwars.models.FilmData
import com.techflix.starwars.models.PeopleApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsService {
    @GET("people")
    suspend fun getStarData(): Response<PeopleApiResponse>

    @GET
    suspend fun getFilmsData(@Url url: String): Response<FilmData>

}