package com.example.movieretrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieretrofit.databinding.FragmentDetailMovieBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailMovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object{
        const val EXTRA_MOVIE = "com.example.movieretrofit.ui.DetailMovieFragment"
    }

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailMovieFragmentArgs by navArgs()

        val movie = args.movie

        binding.title.text = movie.title
        binding.overview.text = movie.overview

        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w500${movie.image}")
            .into(binding.imageMovie)
        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/w500${movie.imageBackdrop}")
            .into(binding.imageBackdrop)
    }

}