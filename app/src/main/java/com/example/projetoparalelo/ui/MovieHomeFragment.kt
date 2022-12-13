package com.example.projetoparalelo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projetoparalelo.Constants.MOVIE_KEY
import com.example.projetoparalelo.R
import com.example.projetoparalelo.ViewState.ViewState
import com.example.projetoparalelo.data.model.MovieResult
import com.example.projetoparalelo.databinding.FragmentMovieHomeBinding
import com.example.projetoparalelo.ui.adapter.MovieAdapter

class MovieHomeFragment : Fragment() {

    private lateinit var binding : FragmentMovieHomeBinding

    private val viewModel: MovieHomeViewModel by lazy {
        ViewModelProvider(this)[MovieHomeViewModel::class.java]
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(arrayListOf(), this::goToMovieDetail )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllMovies()
        initObserver()
        initRecyclerView()

    }

    private fun initRecyclerView() {
            binding.rvMovieHome01.adapter = adapter
//            binding.rvMovieHome01.layoutManager = LinearLayoutManager(context)
            binding.rvMovieHome01.layoutManager = GridLayoutManager(context,2)
    }

    private fun getAllMovies() {
        viewModel.getAllMovies()
    }

    private fun initObserver() {
        viewModel.movieListState.observe(this.viewLifecycleOwner){
            when(it){
                is ViewState.Success -> {
                adapter.updateMovieList(it.data as MutableList<MovieResult>)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "Não foi possivel carregar a lista de filmes da internet",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
        viewModel.loading.observe(this.viewLifecycleOwner){
            if(it is ViewState.Loading){
                binding.progressBarRv.isVisible = it.loading
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieHomeBinding.inflate(inflater)
        return binding.root
    }

    private fun goToMovieDetail(movie: MovieResult) {
        val bundle = bundleOf(MOVIE_KEY to movie)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_movieHomeFragment_to_movieDetailFragment, bundle
        )
    }
}