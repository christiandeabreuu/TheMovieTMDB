package com.example.projetoparalelo.data.repository

import com.example.projetoparalelo.data.model.MovieResponse
import com.example.projetoparalelo.data.remote.RetrofitService

class MovieRepositoryImpl: MovieRepository {
    override suspend fun getAllMoviesNetwork(language: String?): MovieResponse {
        return RetrofitService.apiService.getAllMoviesNetwork(
            language = language
        )
    }
}