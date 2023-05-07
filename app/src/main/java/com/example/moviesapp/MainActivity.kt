package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MoviesResponse
import com.example.moviesapp.services.MovieApiService
import com.example.moviesapp.services.MoviesApiInterface
import com.example.moviesapp.ui.theme.MoviesAppTheme
import kotlinx.android.synthetic.main.activity_main.np_movies_list
import kotlinx.android.synthetic.main.activity_main.rv_movies_list
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movies_list.layoutManager =LinearLayoutManager(this)
        rv_movies_list.setHasFixedSize(true)
        getTopMovieData { movies: List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }

        np_movies_list.layoutManager =LinearLayoutManager(this)
        np_movies_list.setHasFixedSize(true)
        getNowPlayingMovieData { movies: List<Movie> ->
            np_movies_list.adapter = MovieAdapter(movies)
        }
    }

    private fun getTopMovieData(callback : (List<Movie>)-> Unit){
        val apiService = MovieApiService.getInstance().create(MoviesApiInterface::class.java)
        apiService.getTopList().enqueue(object : Callback<MoviesResponse>{
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
        apiService.getNowPlayingList().enqueue(object : Callback<MoviesResponse>{
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
}
