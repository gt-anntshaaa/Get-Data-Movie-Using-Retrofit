package com.example.movieretrofit.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieretrofit.R
import com.example.movieretrofit.data.model.Movie
import com.example.movieretrofit.data.remote.MovieResponse
import com.example.movieretrofit.databinding.ItemMovieBinding
import com.example.movieretrofit.ui.DetailMovieFragment
import com.example.movieretrofit.ui.MovieFragmentDirections

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindView(movie: Movie){
            binding.titleMovie.text = movie.title
            binding.ratingMovie.text = movie.rating

            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500${movie.image}")
                .into(binding.imageMovie)

            binding.root.setOnClickListener {
                val bundle = bundleOf(DetailMovieFragment.EXTRA_MOVIE to movie)
                val action = MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(movie)
                it.findNavController().navigate(action)
            }
        }
    }

    private val listMovie = mutableListOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemMovieBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovie[position]

        holder.bindView(movie)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    fun addItem(itemMovie: List<Movie>){
        listMovie.apply {
            clear()
            addAll(itemMovie)
            notifyDataSetChanged()
        }
    }
}