package com.example.moviesapp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MoviesResponse
import com.example.moviesapp.services.MovieApiService
import com.example.moviesapp.services.MoviesApiInterface
import kotlinx.android.synthetic.main.activity_main.frameContainer
import kotlinx.android.synthetic.main.fragment_fragmento1.np_movies_list
import kotlinx.android.synthetic.main.fragment_fragmento1.rv_movies_list
import kotlinx.android.synthetic.main.movies_item.view.ItemEntero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragmento1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragmento1 : android.app.Fragment() {
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

    override fun onDetach() {
        super.onDetach()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rv_movies_list.layoutManager = LinearLayoutManager(context)
        rv_movies_list.setHasFixedSize(true)
        getTopMovieData { movies: List<Movie> ->
            var adapterTop = MovieAdapter(movies)
            rv_movies_list.adapter = adapterTop
            adapterTop.setOnItemClickListener(object : MovieAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(context,"Is clicking Top no. $position",Toast.LENGTH_SHORT).show()
                    replaceFragment(Fragmento2(),movies[position])
                }
            })
        }

        np_movies_list.layoutManager = LinearLayoutManager(context)
        np_movies_list.setHasFixedSize(true)
        getNowPlayingMovieData { movies: List<Movie> ->
            var adapterNP = MovieAdapter(movies)
            np_movies_list.adapter = adapterNP
            adapterNP.setOnItemClickListener(object : MovieAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(context,"Is clicking NP  $position",Toast.LENGTH_SHORT).show()
                    replaceFragment(Fragmento2(),movies[position])

                }
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragmento1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragmento1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun getTopMovieData(callback : (List<Movie>)-> Unit){
        val apiService = MovieApiService.getInstance().create(MoviesApiInterface::class.java)
        apiService.getTopList().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getNowPlayingMovieData(callback : (List<Movie>)-> Unit){
        val apiService = MovieApiService.getInstance().create(MoviesApiInterface::class.java)
        apiService.getNowPlayingList().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun replaceFragment(Fragment: android.app.Fragment,movie:Movie) {
        val bundle = bundleOf( TITLE_BUNDLE to  movie.title,
        VOTEAVG_BUNDLE to movie.voteavg,
        OVERVIEW_BUNDLE to movie.overview,
        BACKDROP_BUNDLE to movie.backdrop)
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        Fragment.arguments = bundle
        fragmentTransaction.replace(R.id.frameContainer,Fragment)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }


}