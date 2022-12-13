package com.example.projetoparalelo.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.projetoparalelo.Constants.MOVIE_KEY
import com.example.projetoparalelo.Constants.URL_BASE_IMAGE
import com.example.projetoparalelo.data.model.MovieResult
import com.example.projetoparalelo.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMovieDetails()
    }

    private fun showMovieDetails(){
    val movie = arguments?.getParcelable<MovieResult>(MOVIE_KEY)

        movie?.let {
            binding.tvDetailMovie.text = it.title
            binding.tv2DetailMovie.text = it.overview

            Uri.parse(URL_BASE_IMAGE)
                .buildUpon()
                .appendEncodedPath(it.posterPath)
                .build().apply {
                    binding.ivDetailMovie.load(this)
                }
        }
    }
}
//        movie?.let {
//            Picasso.get().load(URL_BASE_IMAGE + it.posterPath)
//                .into(binding.imageView)

