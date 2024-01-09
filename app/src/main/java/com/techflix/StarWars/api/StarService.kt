package com.techflix.StarWars.api

import com.techflix.StarWars.models.PeopleApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface StarService {
    @GET("people")
    suspend fun getStarData(): Response<PeopleApiResponse>

}