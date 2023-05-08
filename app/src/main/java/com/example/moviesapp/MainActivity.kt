package com.example.moviesapp

import android.app.Fragment
import android.os.Bundle
//import android.text.TextUtils.replace
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.models.Movie
import com.example.moviesapp.models.MoviesResponse
import com.example.moviesapp.services.MovieApiService
import com.example.moviesapp.services.MoviesApiInterface
import com.example.moviesapp.ui.theme.MoviesAppTheme
import kotlinx.android.synthetic.main.fragment_fragmento1.np_movies_list
import kotlinx.android.synthetic.main.fragment_fragmento1.rv_movies_list
import kotlinx.android.synthetic.main.movies_item.view.ItemEntero
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        setTheme(R.style.Theme_MoviesApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(Fragmento1())


    }

    fun replaceFragment(Fragment: Fragment) {

        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameContainer,Fragment)
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }


}
