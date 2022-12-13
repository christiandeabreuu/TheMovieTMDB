package com.example.projetoparalelo.domain


import com.example.projetoparalelo.ViewState.ViewState
import com.example.projetoparalelo.data.model.MovieResult
import com.example.projetoparalelo.data.repository.MovieRepository
import com.example.projetoparalelo.data.repository.MovieRepositoryImpl

class MovieUseCase {

    private val movieRepository: MovieRepository = MovieRepositoryImpl()

    suspend fun getAllMovies(): ViewState<List<MovieResult>> {
        return try {
            val movies = movieRepository.getAllMoviesNetwork(language = "pt-br")
            ViewState.Success(movies.movieResults)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes do Banco de Dados!"))
        }
    }
}