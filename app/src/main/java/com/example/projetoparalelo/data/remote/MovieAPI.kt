package com.example.projetoparalelo.data.remote

import com.example.projetoparalelo.data.remote.RetrofitService.Companion.API_KEY
import com.example.projetoparalelo.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getAllMoviesNetwork(
        @Query("api_key")
        apiKey: String? = API_KEY,
        @Query("language")
        language: String?
    ): MovieResponse
}