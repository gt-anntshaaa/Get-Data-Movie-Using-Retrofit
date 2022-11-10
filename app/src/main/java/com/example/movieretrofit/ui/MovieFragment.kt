package com.example.movieretrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieretrofit.data.model.Movie
import com.example.movieretrofit.data.remote.APIClient
import com.example.movieretrofit.data.remote.MovieResponse
import com.example.movieretrofit.databinding.FragmentMovieBinding
import com.example.movieretrofit.ui.adapter.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {
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

    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    private lateinit var movieAdapter: MovieAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup adapter movie
        movieAdapter = MovieAdapter()
        binding.recyclerview.adapter = movieAdapter

        getDataMovie()
    }

    private fun getDataMovie() {
        val call: Call<MovieResponse> = APIClient.service.getMovie("3")

        call!!.enqueue(object: Callback<MovieResponse?>{
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                if (response.isSuccessful){
                    val data = response.body()

                    val listMovie = data?.results?.map {
                        result ->  Movie(
                            title = result.title,
                            rating = result.popularity.toString(),
                            image = result.posterPath,
                            imageBackdrop = result.backdropPath,
                            overview = result.overview
                        )
                    }

                    if (listMovie != null){
                        movieAdapter.addItem(listMovie)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                Log.e("Error", "onFailure: ${t.message}", )
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}