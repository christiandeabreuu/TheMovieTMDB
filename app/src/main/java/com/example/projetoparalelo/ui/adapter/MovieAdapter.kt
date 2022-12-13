package com.example.projetoparalelo.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.projetoparalelo.Constants
import com.example.projetoparalelo.Constants.URL_BASE_IMAGE
import com.example.projetoparalelo.data.model.MovieResponse
import com.example.projetoparalelo.data.model.MovieResult
import com.example.projetoparalelo.databinding.ItemLayoutMoviesBinding
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var listMovies: List<MovieResult>,
    private var clickMovie: (MovieResult) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLayoutMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.showInfo(item)
        holder.binding.ivRvMovie.setOnClickListener {
            clickMovie(item)
        }

    }

    override fun getItemCount() = listMovies.size

    inner class ViewHolder(val binding: ItemLayoutMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showInfo(movie: MovieResult) {
//            val moviePosterUrl : String = URL_BASE_IMAGE + movie.posterPath
//           Picasso.get().load(URL_BASE_IMAGE + movie.posterPath).into(binding.ivRvMovie)
//            Glide.with(itemView.context).load(moviePosterUrl).into(binding.ivRvMovie)
            binding.tvRvMovie.text = movie.title

//            "http://image.tmdb.org/t/p/w500/aDTXsc8uzJuvPLvfFBXTf9rYyTf.jpg"

//            Uri.parse(URL_BASE_IMAGE)
//                .buildUpon()
//                .appendEncodedPath(movie.posterPath)
//                .build().apply {
//                    binding.ivRvMovie.load(this )
//                }



        }
    }

    fun updateMovieList(newList: MutableList<MovieResult>) {
        listMovies = newList
        notifyDataSetChanged()
    }
}