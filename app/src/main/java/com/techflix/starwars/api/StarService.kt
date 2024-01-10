package com.techflix.starwars.api

import com.techflix.starwars.models.PeopleApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface StarService {
    @GET("people")
    suspend fun getStarData(): Response<PeopleApiResponse>

}