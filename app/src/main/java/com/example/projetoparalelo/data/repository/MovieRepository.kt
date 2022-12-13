package com.example.projetoparalelo.data.repository

import com.example.projetoparalelo.data.model.MovieResponse

interface MovieRepository {

    suspend fun getAllMoviesNetwork(language: String?): MovieResponse

}