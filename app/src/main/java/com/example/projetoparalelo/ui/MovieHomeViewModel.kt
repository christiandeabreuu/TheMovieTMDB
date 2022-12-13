package com.example.projetoparalelo.ui

import androidx.lifecycle.*
import com.example.projetoparalelo.ViewState.ViewState
import com.example.projetoparalelo.data.model.MovieResult
import com.example.projetoparalelo.domain.MovieUseCase
import com.example.projetoparalelo.ui.adapter.MovieAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieHomeViewModel: ViewModel() {

    private val movieUseCase = MovieUseCase()
    val movieListState = MutableLiveData<ViewState<List<MovieResult>>>()
    private val _movieListState : LiveData<ViewState<List<MovieResult>>> get() = movieListState
    val loading = MutableLiveData<ViewState<Boolean>>()



    fun getAllMovies() {
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.getAllMovies()
                }
                movieListState.value = response
            } catch (ex: Exception) {
                movieListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista da da internet!"))
            } finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }
}